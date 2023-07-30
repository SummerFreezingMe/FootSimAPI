package com.footsim.service;

import com.footsim.domain.dto.FoulDTO;
import com.footsim.domain.model.Foul;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.Player;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Foul}.
 */
public interface FoulService {
    /**
     * Save a {@link Foul}.
     *
     * @param foulDTO the entity to save.
     * @return the persisted entity.
     */
    FoulDTO save(FoulDTO foulDTO);

    /**
     * Updates a {@link Foul}.
     *
     * @param foulDTO the entity to update.
     * @return the persisted entity.
     */
    FoulDTO update(FoulDTO foulDTO);

    /**
     * Partially updates a {@link Foul}.
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
     * Get the "id" {@link Foul}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    FoulDTO findOne(Long id);

    /**
     * Delete the "id" {@link Foul}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Generate new instance of foul
     * @param roster Players from a team that made a foul
     * @param id id of a current {@link Match}
     * @param minute minute of a {@link Match} at which the foul occurred
     */
    void generateFoul(List<Player> roster, Long id, short minute);

}