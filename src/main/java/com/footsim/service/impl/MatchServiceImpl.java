package com.footsim.service.impl;

import com.footsim.config.Constants;
import com.footsim.domain.dto.MatchDTO;
import com.footsim.domain.enumeration.GoalType;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.Player;
import com.footsim.mapper.MatchMapper;
import com.footsim.repository.ClubRepository;
import com.footsim.repository.MatchRepository;
import com.footsim.repository.PlayerRepository;
import com.footsim.service.MatchService;
import com.footsim.service.exceptions.RosterUnavailableException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;
    private final GoalServiceImpl goalService;
    private final FoulServiceImpl foulService;
    private final ClubServiceImpl clubService;
    private final SeasonStatServiceImpl seasonService;
    private final MatchMapper matchMapper;

    @Override
    public MatchDTO save(MatchDTO matchDTO) {
        Match match = matchMapper.toEntity(matchDTO);
        match = matchRepository.save(match);
        return matchMapper.toDto(match);
    }

    @Override
    public MatchDTO update(MatchDTO matchDTO) {
        Match match = matchMapper.toEntity(matchDTO);
        match = matchRepository.save(match);
        return matchMapper.toDto(match);
    }

    @Override
    public Optional<MatchDTO> partialUpdate(MatchDTO matchDTO) {
        return matchRepository.findById(matchDTO.getId()).map(existingClub -> {
            matchMapper.partialUpdate(existingClub, matchDTO);

            return existingClub;
        }).map(matchRepository::save).map(matchMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<MatchDTO> findAll() {
        return matchRepository.findAll().stream().map(matchMapper::toDto).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Optional<MatchDTO> findOne(Long id) {
        return Optional.ofNullable(matchMapper.toDto(matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match not found with id:" + id)

        )));
    }

    @Override
    public void delete(Long id) {
        matchRepository.deleteById(id);
    }

    @Override
    public MatchDTO simulateMatch(Long id) {
        var homeGoalsTotal = 0L;
        var awayGoalsTotal = 0L;


        var homeTeamOutnumbering = 1.0;

        var match = matchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Match not found with id:" + id));
        var homeClub = clubRepository.findById(match.getHomeClubId()).orElseThrow(
                () -> new EntityNotFoundException("Club not found with id:" + match.getHomeClubId()));
        var awayClub = clubRepository.findById(match.getAwayClubId()).orElseThrow(
                () -> new EntityNotFoundException("Club not found with id:" + match.getAwayClubId()));
        if (clubService.isRosterViable(homeClub) && clubService.isRosterViable(awayClub)) {
            var homeRoster = playerRepository.findByClubIdAndStatus(match.getHomeClubId(), PlayerStatus.ROSTER);
            var awayRoster = playerRepository.findByClubIdAndStatus(match.getAwayClubId(), PlayerStatus.ROSTER);
            double matchCoefficient = homeClub.getRating() * Constants.HOME_CROWD_ADVANTAGE / awayClub.getRating();

            for (short time = 1; time < 50; time += Constants.TIME_LENGTH) {
                var additionalMinutes = 0;
                for (short minute = time; minute < Constants.TIME_LENGTH + time + additionalMinutes; minute++) {

                    long homeGoalsAtMinute = Math.round(Math.random() * matchCoefficient *
                            Constants.GOAL_CHANCE_MULTIPLIER * homeTeamOutnumbering);
                    long awayGoalsAtMinute = Math.round(Math.random() / matchCoefficient *
                            Constants.GOAL_CHANCE_MULTIPLIER / homeTeamOutnumbering);
                    long homeFoulsAtMinute = Math.round(Math.random() * matchCoefficient *
                            Constants.FOUL_CHANCE_MULTIPLIER);
                    long awayFoulsAtMinute = Math.round(Math.random() / matchCoefficient *
                            Constants.FOUL_CHANCE_MULTIPLIER);


                    if (homeGoalsAtMinute > 0) {
                        GoalType goalType = GoalType.getType(Math.random());
                        if (goalType == GoalType.AUTOGOAL) {
                            goalService.generateGoal(awayRoster, id, minute, goalType);
                        } else {
                            goalService.generateGoal(homeRoster, id, minute, goalType);
                        }
                        homeGoalsTotal++;
                        additionalMinutes++;
                    }
                    //todo: implement realistic goal assist distribution
                    if (awayGoalsAtMinute > 0) {
                        GoalType goalType = GoalType.getType(Math.random());
                        if (goalType == GoalType.AUTOGOAL) {
                            goalService.generateGoal(homeRoster, id, minute, goalType);
                        } else {
                            goalService.generateGoal(awayRoster, id, minute, goalType);
                        }
                        awayGoalsTotal++;
                        additionalMinutes += 0.25;
                    }
                    if (homeFoulsAtMinute > 0) {
                        foulService.generateFoul(homeRoster, id, minute);
                        homeTeamOutnumbering = (homeRoster.size() + 100d) / (awayRoster.size() + 100d);
                        additionalMinutes += 0.25;
                    }
                    if (awayFoulsAtMinute > 0) {
                        foulService.generateFoul(awayRoster, id, minute);
                        homeTeamOutnumbering = (homeRoster.size() + 100d) / (awayRoster.size() + 100d);
                        additionalMinutes += 0.25;
                    }
                }
            }

            match.setHomeGoals(homeGoalsTotal);
            match.setAwayGoals(awayGoalsTotal);
            seasonService.addPoints(homeGoalsTotal, awayGoalsTotal, match);

            foulsDiscard(homeRoster);
            foulsDiscard(awayRoster);
            return matchMapper.toDto(match);
        } else throw new RosterUnavailableException();
    }

    @Override
    public void foulsDiscard(List<Player> club) {
        for (Player player : club) {
            switch (player.getStatus()) {
                case SENT_OFF -> player.setStatus(PlayerStatus.DISQUALIFIED);
                //considering there is only 1 match disqualification
                case DISQUALIFIED -> player.setStatus(PlayerStatus.OUT);
            }
            playerRepository.save(player);
        }
    }
}
