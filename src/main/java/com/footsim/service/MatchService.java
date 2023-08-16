package com.footsim.service;

import com.footsim.domain.dto.MatchDTO;
import com.footsim.domain.model.Club;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.Player;

import java.util.List;
import java.util.Optional;
/**
 * Service Interface for managing {@link Match}.
 */
public interface MatchService {
    /**
     * Save a Match.
     *
     * @param MatchDTO the entity to save.
     * @return the persisted entity.
     */
    MatchDTO save(MatchDTO MatchDTO);

    /**
     * Updates a {@link Match}.
     *
     * @param MatchDTO the entity to update.
     * @return the persisted entity.
     */
    MatchDTO update(MatchDTO MatchDTO);

    /**
     * Partially updates a {@link Match}.
     *
     * @param MatchDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MatchDTO> partialUpdate(MatchDTO MatchDTO);

    /**
     * Get all the Matches.
     *
     * @return the list of entities.
     */
    List<MatchDTO> findAll();

    /**
     * Get the "id" {@link Match}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MatchDTO> findOne(Long id);

    /**
     * Delete the "id" {@link Match}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Simulate the result of the {@link Match}
     * @param id of the match
     * @return simulated {@link MatchDTO}
     */
    MatchDTO simulateMatch(Long id);

    /**
     * Removes/adds disqualification after the {@link Match} is done
     * @param team {@link Club} to modify
     */
    void foulsDiscard(List<Player> team);
}
