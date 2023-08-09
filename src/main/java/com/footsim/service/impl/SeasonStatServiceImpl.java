package com.footsim.service.impl;


import com.footsim.domain.dto.SeasonStatDTO;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.SeasonStat;
import com.footsim.domain.model.Club;
import com.footsim.mapper.SeasonStatMapper;
import com.footsim.repository.SeasonStatRepository;
import com.footsim.repository.ClubRepository;
import com.footsim.service.SeasonStatService;
import lombok.AllArgsConstructor;
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
 * Service Implementation for managing {@link SeasonStat}.
 */
@Service
@Transactional
@AllArgsConstructor
public class SeasonStatServiceImpl implements SeasonStatService {

    private final Logger log = LoggerFactory.getLogger(SeasonStatServiceImpl.class);
    private final SeasonStatRepository seasonRepository;
    private final ClubRepository clubRepository;
    private final SeasonStatMapper seasonMapper;

    @Override
    public SeasonStatDTO save(SeasonStatDTO seasonDTO) {
        log.debug("Request to save Season : {}", seasonDTO);
        SeasonStat season = seasonMapper.toEntity(seasonDTO);
        season = seasonRepository.save(season);
        return seasonMapper.toDto(season);
    }

    @Override
    public SeasonStatDTO update(SeasonStatDTO seasonDTO) {
        log.debug("Request to update Season : {}", seasonDTO);
        SeasonStat season = seasonMapper.toEntity(seasonDTO);
        season = seasonRepository.save(season);
        return seasonMapper.toDto(season);
    }

    @Override
    public Optional<SeasonStatDTO> partialUpdate(SeasonStatDTO seasonDTO) {
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
    public List<SeasonStatDTO> findAll() {
        log.debug("Request to get all Seasons");
        return seasonRepository.findAll().stream().map(seasonMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public SeasonStatDTO findOne(Long id) {
        log.debug("Request to get Season : {}", id);
        return seasonRepository.findById(id).map(seasonMapper::toDto).orElse(null);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Season : {}", id);
        seasonRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> initializeSeason(Long seasonId) {
        log.debug("Request to initialize Season : {}", seasonId);
        if (seasonRepository.countAllBySeasonId(seasonId) == 0L) {
            List<Club> seasonClubs = clubRepository.findAllByLeagueId(seasonId);
            for (Club club : seasonClubs) {
                SeasonStat season = new SeasonStat(0L, seasonId, club.getId(),
                        0L, 0L, 0L, 0L, 0L, 0L);
                seasonRepository.save(season);
            }//todo: exception
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public void addPoints(long homeGoalsTotal, long awayGoalsTotal, Match match) {
        log.debug("Request to add points according to Match: {}", match.getId());
        Long leagueId = clubRepository.findById(match.getHomeClubId()).
                orElseThrow().getLeagueId();
        SeasonStat homeClubSeason = seasonRepository.findBySeasonIdAndClubId(
                leagueId, match.getHomeClubId()).orElseThrow();
        SeasonStat awayClubSeason = seasonRepository.findBySeasonIdAndClubId(
                leagueId, match.getAwayClubId()).orElseThrow();

        if (homeGoalsTotal > awayGoalsTotal) {

            homeClubSeason.setPoints(homeClubSeason.getPoints() + 3);

            homeClubSeason.setWins(homeClubSeason.getWins()+1);
            awayClubSeason.setDefeats(awayClubSeason.getDefeats()+1);

        } else if (homeGoalsTotal < awayGoalsTotal) {

            awayClubSeason.setPoints(awayClubSeason.getPoints() + 3);

            awayClubSeason.setWins(awayClubSeason.getWins()+1);
            homeClubSeason.setDefeats(homeClubSeason.getDefeats()+1);
        } else {

            homeClubSeason.setPoints(homeClubSeason.getPoints() + 1);
            awayClubSeason.setPoints(awayClubSeason.getPoints() + 1);

            homeClubSeason.setWins(homeClubSeason.getDraws()+1);
            awayClubSeason.setDefeats(awayClubSeason.getDraws()+1);
        }

        homeClubSeason.setGoalsScored(homeClubSeason.getGoalsScored()+homeGoalsTotal);
        homeClubSeason.setGoalsConceded(homeClubSeason.getGoalsConceded()+awayGoalsTotal);
        awayClubSeason.setGoalsScored(awayClubSeason.getGoalsScored()+awayGoalsTotal);
        awayClubSeason.setGoalsConceded(awayClubSeason.getGoalsConceded()+homeGoalsTotal);

        seasonRepository.save(homeClubSeason);
        seasonRepository.save(awayClubSeason);
    }
}
