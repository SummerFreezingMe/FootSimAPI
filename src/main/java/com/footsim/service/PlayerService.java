package com.footsim.service;

import com.footsim.domain.dto.PlayerDTO;
import com.footsim.domain.dto.TransferDTO;
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
     * Save a {@link Player}.
     *
     * @param PlayerDTO the entity to save.
     * @return the persisted entity.
     */
    PlayerDTO save(PlayerDTO PlayerDTO);

    /**
     * Updates a {@link Player}.
     *
     * @param PlayerDTO the entity to update.
     * @return the persisted entity.
     */
    PlayerDTO update(PlayerDTO PlayerDTO);

    /**
     * Partially updates a {@link Player}.
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
     * Get the "id" {@link Player}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    PlayerDTO findOne(Long id);

    /**
     * Delete the "id" {@link Player}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);


    /**
     * Switch status of a {@link Player}
     * @param id the id of the {@link Player}
     * @param status {@link PlayerStatus} that has to be set on a chosen {@link Player}
     * @return DTO of redacted {@link Player}
     */
    PlayerDTO switchStatus(Long id, PlayerStatus status);

    /**
     * Perform player's transfer from one team from another
     * @param transfer DTO for transfer operation
     * @return DTO of transferred {@link Player}
     */
    PlayerDTO transferPlayer(TransferDTO transfer);

    /** Release of a {@link Player}
     * @param playerDTO DTO of a {@link Player} to release
     * @return released {@link Player} DTO
     */
    PlayerDTO releasePlayer(PlayerDTO playerDTO);
}
