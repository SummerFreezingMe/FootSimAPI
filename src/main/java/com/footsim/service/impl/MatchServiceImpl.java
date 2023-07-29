package com.footsim.service.impl;

import com.footsim.config.Constants;
import com.footsim.domain.dto.MatchDTO;
import com.footsim.domain.enumeration.GoalType;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.Player;
import com.footsim.mapper.MatchMapper;
import com.footsim.repository.MatchRepository;
import com.footsim.repository.PlayerRepository;
import com.footsim.repository.TeamRepository;
import com.footsim.service.MatchService;
import com.footsim.service.exceptions.RosterUnavailableException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {
    private final Logger log = LoggerFactory.getLogger(MatchServiceImpl.class);

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final GoalServiceImpl goalService;
    private final FoulServiceImpl foulService;

    private final TeamServiceImpl teamService;
    private final PlayerRepository playerRepository;
    private final MatchMapper matchMapper;

    public MatchServiceImpl(MatchRepository matchRepository,
                            TeamRepository teamRepository,
                            GoalServiceImpl goalService,
                            FoulServiceImpl foulService, TeamServiceImpl teamService, PlayerRepository playerRepository,
                            MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.goalService = goalService;
        this.foulService = foulService;
        this.teamService = teamService;
        this.playerRepository = playerRepository;
        this.matchMapper = matchMapper;
    }


    @Override
    public MatchDTO save(MatchDTO matchDTO) {
        log.debug("Request to save Team : {}", matchDTO);
        Match match = matchMapper.toEntity(matchDTO);
        match = matchRepository.save(match);
        return matchMapper.toDto(match);
    }

    @Override
    public MatchDTO update(MatchDTO matchDTO) {
        log.debug("Request to update Match : {}", matchDTO);
        Match match = matchMapper.toEntity(matchDTO);
        match = matchRepository.save(match);
        return matchMapper.toDto(match);
    }

    @Override
    public Optional<MatchDTO> partialUpdate(MatchDTO matchDTO) {
        log.debug("Request to partially update Match : {}", matchDTO);
        return matchRepository
                .findById(matchDTO.getId())
                .map(existingTeam -> {
                    matchMapper.partialUpdate(existingTeam, matchDTO);

                    return existingTeam;
                })
                .map(matchRepository::save)
                .map(matchMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<MatchDTO> findAll() {
        log.debug("Request to get all Matches");
        return matchRepository.findAll().stream()
                .map(matchMapper::toDto).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Optional<MatchDTO> findOne(Long id) {
        log.debug("Request to get Match : {}", id);
        return Optional.ofNullable(matchMapper.toDto(matchRepository.findById(id).orElse(null)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Team : {}", id);
        matchRepository.deleteById(id);
    }

    @Override
    public MatchDTO simulateMatch(Long id) {
        var homeGoalsTotal = 0L;
        var awayGoalsTotal = 0L;

        var match = matchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Match not found with id:"+id));
        var homeTeam = teamRepository.findById(match.getHomeTeamId()).orElseThrow(
                () -> new EntityNotFoundException("Team not found with id:"+match.getHomeTeamId()));
        var awayTeam = teamRepository.findById(match.getAwayTeamId()).orElseThrow(
                () -> new EntityNotFoundException("Team not found with id:"+match.getAwayTeamId()));
        if (teamService.isRosterViable(homeTeam) && teamService.isRosterViable(awayTeam)
        ) {
            var homeRoster = playerRepository.findByClubIdAndStatus(match.getHomeTeamId(),
                    PlayerStatus.ROSTER);
            var awayRoster = playerRepository.findByClubIdAndStatus(match.getAwayTeamId(),
                    PlayerStatus.ROSTER);
            double matchCoefficient = homeTeam.getRating() *
                    Constants.HOME_CROWD_ADVANTAGE / awayTeam.getRating();

            for (short time = 1; time < 50; time += Constants.TIME_LENGTH) {
                var additionalMinutes = 0;
                for (short minute = time; minute < Constants.TIME_LENGTH + time + additionalMinutes; minute++) {

                    long homeGoalsAtMinute = Math.round(Math.random() * matchCoefficient * Constants.GOAL_CHANCE_MULTIPLIER);
                    long awayGoalsAtMinute = Math.round(Math.random() / matchCoefficient * Constants.GOAL_CHANCE_MULTIPLIER);
                    long homeFoulsAtMinute = Math.round(Math.random() * matchCoefficient * Constants.FOUL_CHANCE_MULTIPLIER);
                    long awayFoulsAtMinute = Math.round(Math.random() / matchCoefficient * Constants.FOUL_CHANCE_MULTIPLIER);


                    if (homeGoalsAtMinute > 0) {
                        GoalType goalType = GoalType.getType(Math.random());
                        if (goalType == GoalType.AUTOGOAL) {
                            goalService.generateGoal(awayRoster, id, minute,goalType);
                        } else {
                            goalService.generateGoal(homeRoster, id, minute,goalType);
                        }
                        homeGoalsTotal++;
                        additionalMinutes++;
                    }
                    //todo: implement realistic goal assist distribution
                    if (awayGoalsAtMinute > 0) {
                        GoalType goalType = GoalType.getType(Math.random());
                        if (goalType == GoalType.AUTOGOAL) {
                            goalService.generateGoal(homeRoster, id, minute,goalType);
                        } else {
                            goalService.generateGoal(awayRoster, id, minute,goalType);
                        }
                        awayGoalsTotal++;
                        additionalMinutes += 0.25;
                    }
                    if (homeFoulsAtMinute > 0) {
                        foulService.generateFoul(homeRoster, id, minute);
                        additionalMinutes += 0.25;
                    }
                    if (awayFoulsAtMinute > 0) {
                        foulService.generateFoul(awayRoster, id, minute);
                        additionalMinutes++;
                    }
                }
            }

            match.setHomeGoals(homeGoalsTotal);
            match.setAwayGoals(awayGoalsTotal);

            foulsDiscard(homeRoster);
            foulsDiscard(awayRoster);
            return matchMapper.toDto(match);
        }else throw new RosterUnavailableException();
    }

    @Override
    public void foulsDiscard(List<Player> team) {
        for (Player player : team
        ) {
            switch (player.getStatus()) {
                case SENT_OFF -> player.setStatus(PlayerStatus.DISQUALIFIED);
                //considering there is only 1 match disqualification
                case DISQUALIFIED -> player.setStatus(PlayerStatus.OUT);
            }
            playerRepository.save(player);
        }
    }
}
