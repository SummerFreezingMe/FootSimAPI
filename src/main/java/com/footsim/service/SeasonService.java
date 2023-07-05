package com.footsim.service;

import com.footsim.domain.dto.SeasonDTO;
import com.footsim.domain.model.Season;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Season}.
 */
public interface SeasonService {
    /**
     * Save a Season.
     *
     * @param SeasonDTO the entity to save.
     * @return the persisted entity.
     */
    SeasonDTO save(SeasonDTO SeasonDTO);

    /**
     * Updates a Season.
     *
     * @param SeasonDTO the entity to update.
     * @return the persisted entity.
     */
    SeasonDTO update(SeasonDTO SeasonDTO);

    /**
     * Partially updates a Season.
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
     * Get the "id" Season.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    SeasonDTO findOne(Long id);

    /**
     * Delete the "id" Season.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
