package com.footsim.service;

import com.footsim.domain.dto.ClubDTO;
import com.footsim.domain.model.Club;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Club}.
 */
public interface ClubService {
    /**
     * Save a {@link Club}.
     *
     * @param ClubDTO the entity to save.
     * @return the persisted entity.
     */
    ClubDTO save(ClubDTO ClubDTO);

    /**
     * Updates a {@link Club}.
     *
     * @param ClubDTO the entity to update.
     * @return the persisted entity.
     */
    ClubDTO update(ClubDTO ClubDTO);

    /**
     * Partially updates a {@link Club}.
     *
     * @param ClubDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClubDTO> partialUpdate(ClubDTO ClubDTO);

    /**
     * Get all the Clubs.
     *
     * @return the list of entities.
     */
    List<ClubDTO> findAll();

    /**
     * Get the "id" {@link Club}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    ClubDTO findOne(Long id);

    /**
     * Delete the "id" {@link Club}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Count rating of the {@link Club}.
     * @param id id of the {@link Club}
     * @return the entity
     */
    ClubDTO countClubRating(Long id);

    /**
     * Checking is roster are viable for a game.
     * @param club {@link Club} for a check
     * @return true if a {@link Club} are viable, false if otherwise.
     */
    boolean isRosterViable(Club club);
}
