package com.footsim.service;

import com.footsim.domain.dto.SeasonStatDTO;
import com.footsim.domain.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SeasonStat}.
 */
public interface SeasonStatService {
    /**
     * Save a {@link SeasonStat}.
     *
     * @param seasonDTO the entity to save.
     * @return the persisted entity.
     */
    SeasonStatDTO save(SeasonStatDTO seasonDTO);

    /**
     * Updates a {@link SeasonStat}.
     *
     * @param seasonDTO the entity to update.
     * @return the persisted entity.
     */
    SeasonStatDTO update(SeasonStatDTO seasonDTO);

    /**
     * Partially updates a {@link SeasonStat}.
     *
     * @param seasonDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SeasonStatDTO> partialUpdate(SeasonStatDTO seasonDTO);

    /**
     * Get all the Stats.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    List<SeasonStatDTO> findAll();

    /**
     * Get the "id" {@link SeasonStat}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    SeasonStatDTO findOne(Long id);

    /**
     * Delete the "id" {@link SeasonStat}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Initialize {@link Season} by adding all teams from {@link League} with 0 points
     *
     * @param seasonId Id of {@link Season}
     * @return {@link ResponseEntity} with operation status
     */
    ResponseEntity<?> initializeSeason(Long seasonId);

    /**
     * add points according to result of {@link Match}
     *
     * @param homeGoalsTotal amount of goals scored by {@link Team} at home
     * @param awayGoalsTotal amount of goals scored {@link Team} away
     * @param match {@link Match} entity
     */
    void addPoints(long homeGoalsTotal, long awayGoalsTotal, Match match);
}
