package com.footsim.service;

import com.footsim.domain.dto.MatchDTO;
import com.footsim.domain.model.Match;


import java.util.List;
import java.util.Optional;
/**
 * Service Interface for managing {@link Match}.
 */
public interface MatchService {
    /**
     * Save an Match.
     *
     * @param MatchDTO the entity to save.
     * @return the persisted entity.
     */
    MatchDTO save(MatchDTO MatchDTO);

    /**
     * Updates a Match.
     *
     * @param MatchDTO the entity to update.
     * @return the persisted entity.
     */
    MatchDTO update(MatchDTO MatchDTO);

    /**
     * Partially updates an Match.
     *
     * @param MatchDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MatchDTO> partialUpdate(MatchDTO MatchDTO);

    /**
     * Get all the Matchs.
     *
     * @return the list of entities.
     */
    List<MatchDTO> findAll();

    /**
     * Get the "id" Match.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MatchDTO> findOne(Long id);

    /**
     * Delete the "id" Match.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
