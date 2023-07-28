package com.footsim.service;

import com.footsim.domain.dto.SeasonDTO;
import com.footsim.domain.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SeasonStat}.
 */
public interface SeasonStatService {
    /**
     * Save a {@link SeasonStat}.
     *
     * @param SeasonDTO the entity to save.
     * @return the persisted entity.
     */
    SeasonDTO save(SeasonDTO SeasonDTO);

    /**
     * Updates a {@link SeasonStat}.
     *
     * @param SeasonDTO the entity to update.
     * @return the persisted entity.
     */
    SeasonDTO update(SeasonDTO SeasonDTO);

    /**
     * Partially updates a {@link SeasonStat}.
     *
     * @param SeasonDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SeasonDTO> partialUpdate(SeasonDTO SeasonDTO);

    /**
     * Get all the Seasons.
     *
     * @return the list of entities.
     */
    List<SeasonDTO> findAll();

    /**
     * Get the "id" {@link SeasonStat}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    SeasonDTO findOne(Long id);

    /**
     * Delete the "id" {@link SeasonStat}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Initialize {@link SeasonStat} with each {@link Team} from the {@link League} added with 0 points
     *
     * @param seasonId Id of the {@link Season}
     * @return status of operation wrapped in {@link ResponseEntity}
     */

    ResponseEntity<?> initializeSeason(Long seasonId);

    /**
     * Add points to teams according to {@link Match} result
     *
     * @param homeGoalsTotal amount of goals of a {@link Team} at home
     * @param awayGoalsTotal amount of goals of a {@link Team} away
     * @param match          {@link Match} entity
     */
    void addPoints(long homeGoalsTotal, long awayGoalsTotal, Match match);
}
