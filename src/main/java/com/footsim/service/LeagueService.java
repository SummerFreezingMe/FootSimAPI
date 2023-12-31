package com.footsim.service;

import com.footsim.domain.dto.LeagueDTO;
import com.footsim.domain.model.League;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link League}.
 */
public interface LeagueService {
    /**
     * Save a {@link League}.
     *
     * @param LeagueDTO the entity to save.
     * @return the persisted entity.
     */
    LeagueDTO save(LeagueDTO LeagueDTO);

    /**
     * Updates a {@link League}.
     *
     * @param LeagueDTO the entity to update.
     * @return the persisted entity.
     */
    LeagueDTO update(LeagueDTO LeagueDTO);

    /**
     * Partially updates a {@link League}.
     *
     * @param LeagueDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LeagueDTO> partialUpdate(LeagueDTO LeagueDTO);

    /**
     * Get all the Leagues.
     *
     * @return the list of entities.
     */
    List<LeagueDTO> findAll();

    /**
     * Get the "id" {@link League}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    LeagueDTO findOne(Long id);

    /**
     * Delete the "id" {@link League}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
