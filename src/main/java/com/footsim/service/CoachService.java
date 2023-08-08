package com.footsim.service;


import com.footsim.domain.dto.CoachDTO;
import com.footsim.domain.dto.TransferDTO;
import com.footsim.domain.model.Coach;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Coach}.
 */
@Service
@Transactional
public interface CoachService {
    /**
     * Save a {@link Coach}.
     *
     * @param CoachDTO the entity to save.
     * @return the persisted entity.
     */
    CoachDTO save(CoachDTO CoachDTO);

    /**
     * Updates a {@link Coach}.
     *
     * @param CoachDTO the entity to update.
     * @return the persisted entity.
     */
    CoachDTO update(CoachDTO CoachDTO);

    /**
     * Partially updates a {@link Coach}.
     *
     * @param CoachDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CoachDTO> partialUpdate(CoachDTO CoachDTO);

    /**
     * Get all the Coachs.
     *
     * @return the list of entities.
     */
    List<CoachDTO> findAll();

    /**
     * Get the "id" {@link Coach}.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    CoachDTO findOne(Long id);

    /**
     * Delete the "id" {@link Coach}.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);




    /**
     * Perform coach's transfer from one team from another
     * @param transfer DTO for transfer operation
     * @return DTO of transferred {@link Coach}
     */
    CoachDTO transferCoach(TransferDTO transfer);
}
