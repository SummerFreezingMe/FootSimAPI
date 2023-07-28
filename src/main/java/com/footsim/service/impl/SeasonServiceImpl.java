package com.footsim.service.impl;


import com.footsim.domain.dto.SeasonDTO;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.Season;
import com.footsim.domain.model.Team;
import com.footsim.mapper.SeasonMapper;
import com.footsim.repository.SeasonRepository;
import com.footsim.repository.TeamRepository;
import com.footsim.service.SeasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Season}.
 */
@Service
@Transactional
public class SeasonServiceImpl implements SeasonService {

    private final Logger log = LoggerFactory.getLogger(SeasonServiceImpl.class);

    private final SeasonRepository seasonRepository;

    private final TeamRepository teamRepository;
    private final SeasonMapper seasonMapper;


    public SeasonServiceImpl(SeasonRepository seasonRepository, TeamRepository teamRepository, SeasonMapper seasonMapper) {
        this.seasonRepository = seasonRepository;
        this.teamRepository = teamRepository;
        this.seasonMapper = seasonMapper;
    }

    @Override
    public SeasonDTO save(SeasonDTO seasonDTO) {
        log.debug("Request to save Season : {}", seasonDTO);
        Season season = seasonMapper.toEntity(seasonDTO);
        season = seasonRepository.save(season);
        return seasonMapper.toDto(season);
    }

    @Override
    public SeasonDTO update(SeasonDTO seasonDTO) {
        log.debug("Request to update Season : {}", seasonDTO);
        Season season = seasonMapper.toEntity(seasonDTO);
        season = seasonRepository.save(season);
        return seasonMapper.toDto(season);
    }

    @Override
    public Optional<SeasonDTO> partialUpdate(SeasonDTO seasonDTO) {
        log.debug("Request to partially update Season : {}", seasonDTO);
        return seasonRepository
                .findById(seasonDTO.getId())
                .map(existingSeason -> {
                    seasonMapper.partialUpdate(existingSeason, seasonDTO);

                    return existingSeason;
                })
                .map(seasonRepository::save)
                .map(seasonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeasonDTO> findAll() {
        log.debug("Request to get all Seasons");
        return seasonRepository.findAll().stream().map(seasonMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public SeasonDTO findOne(Long id) {
        log.debug("Request to get Season : {}", id);
        return seasonRepository.findById(id).map(seasonMapper::toDto).orElse(null);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Season : {}", id);
        seasonRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> initializeSeason(Long leagueId, Integer year) {
        if (seasonRepository.countAllByLeagueIdAndYear(leagueId, year) == 0L) {
            List<Team> seasonTeams = teamRepository.findAllByLeagueId(leagueId);
            for (Team team : seasonTeams) {
                Season season = new Season(0L, leagueId, year, team.getId(),
                        0L, 0L, 0L, 0L, 0L, 0L);
                seasonRepository.save(season);
            }//todo: exception
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public void addPoints(long homeGoalsTotal, long awayGoalsTotal, Match match) {
        // todo: workaround for fall-spring leagues
        int yearOfMatch = match.getDate().getYear();
        Long leagueId = teamRepository.findById(match.getHomeTeamId()).
                orElseThrow().getLeagueId();
        Season homeTeamSeason = seasonRepository.findByLeagueIdAndYearAndTeamId(
                leagueId, yearOfMatch, match.getHomeTeamId()).orElseThrow();
        Season awayTeamSeason = seasonRepository.findByLeagueIdAndYearAndTeamId(
                leagueId, yearOfMatch, match.getHomeTeamId()).orElseThrow();

        if (homeGoalsTotal > awayGoalsTotal) {

            homeTeamSeason.setPoints(homeTeamSeason.getPoints() + 3);

            homeTeamSeason.setWins(homeTeamSeason.getWins()+1);
            awayTeamSeason.setDefeats(awayTeamSeason.getDefeats()+1);

        } else if (homeGoalsTotal < awayGoalsTotal) {

            awayTeamSeason.setPoints(awayTeamSeason.getPoints() + 3);

            awayTeamSeason.setWins(awayTeamSeason.getWins()+1);
            homeTeamSeason.setDefeats(homeTeamSeason.getDefeats()+1);
        } else {

            homeTeamSeason.setPoints(homeTeamSeason.getPoints() + 1);
            awayTeamSeason.setPoints(awayTeamSeason.getPoints() + 1);

            homeTeamSeason.setWins(homeTeamSeason.getDraws()+1);
            awayTeamSeason.setDefeats(awayTeamSeason.getDraws()+1);
        }

        homeTeamSeason.setGoalsScored(homeTeamSeason.getGoalsScored()+homeGoalsTotal);
        homeTeamSeason.setGoalsConceded(homeTeamSeason.getGoalsConceded()+awayGoalsTotal);
        awayTeamSeason.setGoalsScored(awayTeamSeason.getGoalsScored()+awayGoalsTotal);
        awayTeamSeason.setGoalsConceded(awayTeamSeason.getGoalsConceded()+homeGoalsTotal);

        seasonRepository.save(homeTeamSeason);
        seasonRepository.save(awayTeamSeason);
    }
}
