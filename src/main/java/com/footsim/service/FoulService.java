package com.footsim.service;

import com.footsim.domain.dto.FoulDTO;
import com.footsim.domain.model.Foul;
import com.footsim.domain.model.Player;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Foul}.
 */
public interface FoulService {
    /**
     * Save an foul.
     *
     * @param foulDTO the entity to save.
     * @return the persisted entity.
     */
    FoulDTO save(FoulDTO foulDTO);

    /**
     * Updates a foul.
     *
     * @param foulDTO the entity to update.
     * @return the persisted entity.
     */
    FoulDTO update(FoulDTO foulDTO);

    /**
     * Partially updates an foul.
     *
     * @param foulDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FoulDTO> partialUpdate(FoulDTO foulDTO);

    /**
     * Get all the fouls.
     *
     * @return the list of entities.
     */
    List<FoulDTO> findAll();

    /**
     * Get the "id" foul.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FoulDTO> findOne(Long id);

    /**
     * Delete the "id" foul.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void generateFoul(List<Player> awayRoster, Long id, short minute);
}