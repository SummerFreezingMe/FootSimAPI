package com.footsim.service.impl;

import com.footsim.config.Constants;
import com.footsim.domain.dto.MatchDTO;
import com.footsim.domain.enumeration.GoalType;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Goal;
import com.footsim.domain.model.Match;
import com.footsim.mapper.MatchMapper;
import com.footsim.repository.GoalRepository;
import com.footsim.repository.MatchRepository;
import com.footsim.repository.PlayerRepository;
import com.footsim.repository.TeamRepository;
import com.footsim.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {
    private final Logger log = LoggerFactory.getLogger(MatchServiceImpl.class);

    Random r = new Random();
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    private final GoalRepository goalRepository;
    private final PlayerRepository playerRepository;
    private final MatchMapper matchMapper;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository, GoalRepository goalRepository, PlayerRepository playerRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.goalRepository = goalRepository;
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
        log.debug("Request to get all Matchs");
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
        var match = matchRepository.findById(id).orElseThrow();
        var homeTeam = teamRepository.findById(match.getHomeTeamId()).orElseThrow();
        var awayTeam = teamRepository.findById(match.getAwayTeamId()).orElseThrow();
        var homeRoster = playerRepository.findByClubIdAndStatus(match.getHomeTeamId(),
                PlayerStatus.ROSTER);
        var awayRoster = playerRepository.findByClubIdAndStatus(match.getAwayTeamId(),
            PlayerStatus.ROSTER);
        double matchCoefficient = homeTeam.getRating()*
                Constants.HOME_CROWD_ADVANTAGE/awayTeam.getRating();
    for (int time = 1; time < 50; time+= Constants.TIME_LENGTH) {

     for (short i = 1; i < Constants.TIME_LENGTH+1; i++) {
        long homeGoalsAtMinute =Math.round(Math.random()*matchCoefficient)/Constants.TIME_LENGTH;
        long awayGoalsAtMinute =  Math.round(Math.random()/matchCoefficient)/Constants.TIME_LENGTH;
    if(homeGoalsAtMinute>0){
        Goal goal = new Goal(0L,id,homeRoster.get(r.nextInt(11)).getId(),
                homeRoster.get(r.nextInt(11)).getId(),i, GoalType.DEFAULT);
    goalRepository.save(goal);
    homeGoalsTotal++;
    }
    //todo: implement realistic goal assist distribution
        if(awayGoalsAtMinute>0){
            Goal goal = new Goal(0L,id,awayRoster.get(r.nextInt(11)).getId(),
                    awayRoster.get(r.nextInt(11)).getId(),i, GoalType.DEFAULT);
            goalRepository.save(goal);
            awayGoalsTotal++;
        }
    }
}


        match.setHomeGoals(homeGoalsTotal);
        match.setAwayGoals(awayGoalsTotal);
        return matchMapper.toDto(match);
    }
}
