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
     * Save a {@link Team}.
     *
     * @param TeamDTO the entity to save.
     * @return the persisted entity.
     */
    TeamDTO save(TeamDTO TeamDTO);

    /**
     * Updates a {@link Team}.
     *
     * @param TeamDTO the entity to update.
     * @return the persisted entity.
     */
    TeamDTO update(TeamDTO TeamDTO);

    /**
     * Partially updates a {@link Team}.
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
     * Get the "id" {@link Team}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    TeamDTO findOne(Long id);

    /**
     * Delete the "id" {@link Team}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Count rating of the {@link Team}.
     * @param id id of the {@link Team}
     * @return the entity
     */
    TeamDTO countTeamRating(Long id);

    /**
     * Checking is roster are viable for a game.
     * @param team {@link Team} for a check
     * @return true if a {@link Team} are viable, false if otherwise.
     */
    boolean isRosterViable(Team team);
}
