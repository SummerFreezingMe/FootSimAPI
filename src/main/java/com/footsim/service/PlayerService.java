package com.footsim.service;

import com.footsim.domain.dto.PlayerDTO;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Service class for managing {@link Player}.
 */
@Service
@Transactional
public interface PlayerService {
    /**
     * Save an Player.
     *
     * @param PlayerDTO the entity to save.
     * @return the persisted entity.
     */
    PlayerDTO save(PlayerDTO PlayerDTO);

    /**
     * Updates a Player.
     *
     * @param PlayerDTO the entity to update.
     * @return the persisted entity.
     */
    PlayerDTO update(PlayerDTO PlayerDTO);

    /**
     * Partially updates an Player.
     *
     * @param PlayerDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PlayerDTO> partialUpdate(PlayerDTO PlayerDTO);

    /**
     * Get all the Players.
     *
     * @return the list of entities.
     */
    List<PlayerDTO> findAll();

    /**
     * Get the "id" Player.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    PlayerDTO findOne(Long id);

    /**
     * Delete the "id" Player.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);



    PlayerDTO switchStatus(Long id, PlayerStatus status);
}
