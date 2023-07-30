package com.footsim.service;

import com.footsim.domain.dto.SeasonStatDTO;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.SeasonStat;
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
     * @param seasonId
     * @return
     */
    ResponseEntity<?> initializeSeason(Long seasonId);

    /**
     * @param homeGoalsTotal
     * @param awayGoalsTotal
     * @param match
     */
    void addPoints(long homeGoalsTotal, long awayGoalsTotal, Match match);
}
