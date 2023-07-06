package com.footsim.service;


import com.footsim.domain.dto.GoalDTO;
import com.footsim.domain.model.Goal;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Goal}.
 */
public interface GoalService {
    /**
     * Save an goal.
     *
     * @param goalDTO the entity to save.
     * @return the persisted entity.
     */
    GoalDTO save(GoalDTO goalDTO);

    /**
     * Updates a goal.
     *
     * @param goalDTO the entity to update.
     * @return the persisted entity.
     */
    GoalDTO update(GoalDTO goalDTO);

    /**
     * Partially updates an goal.
     *
     * @param goalDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GoalDTO> partialUpdate(GoalDTO goalDTO);

    /**
     * Get all the goals.
     *
     * @return the list of entities.
     */
    List<GoalDTO> findAll();

    /**
     * Get the "id" goal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GoalDTO> findOne(Long id);

    /**
     * Delete the "id" goal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
