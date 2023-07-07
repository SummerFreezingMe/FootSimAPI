package com.footsim.service;

import com.footsim.domain.dto.TeamDTO;
import com.footsim.domain.model.Team;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Team}.
 */
public interface TeamService {
    /**
     * Save a Team.
     *
     * @param TeamDTO the entity to save.
     * @return the persisted entity.
     */
    TeamDTO save(TeamDTO TeamDTO);

    /**
     * Updates a Team.
     *
     * @param TeamDTO the entity to update.
     * @return the persisted entity.
     */
    TeamDTO update(TeamDTO TeamDTO);

    /**
     * Partially updates a Team.
     *
     * @param TeamDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TeamDTO> partialUpdate(TeamDTO TeamDTO);

    /**
     * Get all the Teams.
     *
     * @return the list of entities.
     */
    List<TeamDTO> findAll();

    /**
     * Get the "id" Team.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    TeamDTO findOne(Long id);

    /**
     * Delete the "id" Team.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    TeamDTO countTeamRating(Long id);
}
