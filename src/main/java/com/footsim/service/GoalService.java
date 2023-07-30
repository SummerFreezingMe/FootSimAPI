package com.footsim.service;


import com.footsim.domain.dto.GoalDTO;
import com.footsim.domain.dto.TopActionsDTO;
import com.footsim.domain.enumeration.GoalType;
import com.footsim.domain.model.Goal;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Season;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Goal}.
 */
public interface GoalService {
    /**
     * Save a {@link Goal}.
     *
     * @param goalDTO the entity to save.
     * @return the persisted entity.
     */
    GoalDTO save(GoalDTO goalDTO);

    /**
     * Updates a {@link Goal}.
     *
     * @param goalDTO the entity to update.
     * @return the persisted entity.
     */
    GoalDTO update(GoalDTO goalDTO);

    /**
     * Partially updates a {@link Goal}.
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
     * Get the "id" {@link Goal}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    GoalDTO findOne(Long id);

    /**
     * Delete the "id" {@link Goal}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Generate new instance of {@link Goal}
     *
     * @param roster   Players from a team that scored
     * @param id       id of a current {@link Match}
     * @param minute   minute of a {@link Match} at which the goal was scored
     * @param goalType Type of scored {@link Goal}
     */
    void generateGoal(List<Player> roster, Long id, short minute, GoalType goalType);

    /**
     * Displays list of top scorers for specific {@link Season}
     *
     * @param seasonId id of a {@link Season}
     * @return List of id's of a {@link Player} and amount of their goals
     */
    List<TopActionsDTO> displayTopScorers(Long seasonId);

    /**
     * Displays list of top assistants for specific {@link Season}
     *
     * @param seasonId id of a {@link Season}
     * @return List of id's of a {@link Player} and amount of their assists
     */
    List<TopActionsDTO> displayTopAssistants(Long seasonId);
}
