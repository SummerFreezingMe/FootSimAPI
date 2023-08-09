package com.footsim.service.impl;

import com.footsim.domain.dto.CoachDTO;
import com.footsim.domain.dto.TransferDTO;
import com.footsim.domain.model.Coach;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Team;
import com.footsim.mapper.CoachMapper;
import com.footsim.repository.CoachRepository;
import com.footsim.repository.PlayerRepository;
import com.footsim.repository.TeamRepository;
import com.footsim.service.CoachService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Coach}.
 */
@Service
@Transactional
@AllArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final Logger log = LoggerFactory.getLogger(CoachServiceImpl.class);
    private final CoachRepository coachRepository;

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final CoachMapper coachMapper;

    Random r;

    @Override
    public CoachDTO save(CoachDTO coachDTO) {
        log.debug("Request to save Coach : {}", coachDTO);
        Coach coach = coachMapper.toEntity(coachDTO);
        coach = coachRepository.save(coach);
        return coachMapper.toDto(coach);
    }

    @Override
    public CoachDTO update(CoachDTO coachDTO) {
        log.debug("Request to update Coach : {}", coachDTO);
        Coach coach = coachMapper.toEntity(coachDTO);
        coach = coachRepository.save(coach);
        return coachMapper.toDto(coach);
    }

    @Override
    public Optional<CoachDTO> partialUpdate(CoachDTO coachDTO) {
        log.debug("Request to partially update Coach : {}", coachDTO);

        return coachRepository
                .findById(coachDTO.getId())
                .map(existingCoach -> {
                    coachMapper.partialUpdate(existingCoach, coachDTO);

                    return existingCoach;
                })
                .map(coachRepository::save)
                .map(coachMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoachDTO> findAll() {
        log.debug("Request to get all Coaches");
        return coachRepository.findAll().stream().map(coachMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public CoachDTO findOne(Long id) {
        log.debug("Request to get Coach : {}", id);
        Coach coach = coachRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Coach not found with id:" + id)
        );
        return coachMapper.toDto(coach);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Coach : {}", id);
        coachRepository.deleteById(id);
    }

    @Override
    public CoachDTO transferCoach(TransferDTO transfer) {
        log.debug("Request to transfer Coach : {}", transfer.getPersonId());
        Coach transferredCoach = coachRepository.
                findById(transfer.getPersonId()).orElseThrow(
                        () -> new EntityNotFoundException("Coach not found with id:" + transfer.getPersonId()));
        Team fromTeam = teamRepository.
                findById(transfer.getClubFromId()).orElseThrow(
                        () -> new EntityNotFoundException("Team not found with id:" + transfer.getClubFromId()));
        Team toTeam = teamRepository.
                findById(transfer.getClubToId()).orElseThrow(
                        () -> new EntityNotFoundException("Coach not found with id:" + transfer.getClubToId()));
        transferredCoach.setTeamId(transfer.getClubToId());
        toTeam.setBalance(toTeam.getBalance() - transfer.getTransferFee());
        fromTeam.setBalance(fromTeam.getBalance() + transfer.getTransferFee());
        coachRepository.save(transferredCoach);
        teamRepository.save(toTeam);
        teamRepository.save(fromTeam);
        return coachMapper.toDto(transferredCoach);
    }

    @Override
    public CoachDTO retireToCoaching(Player player) {
        log.debug("Request to retire Player to Coaching : {}", player.getId());
        Coach newCoach = new Coach();
        newCoach.setName(player.getName());
        newCoach.setRating(r.nextInt(200));
        coachRepository.save(newCoach);
        playerRepository.delete(player);
        return coachMapper.toDto(newCoach);
    }

    @Override
    public CoachDTO releaseCoach(CoachDTO coachDTO) {
        log.debug("Request to release Coach : {}", coachDTO.getId());
        Coach coach = coachRepository.findById(coachDTO.getId()).orElseThrow(
                () -> new EntityNotFoundException("Coach not found with id:" + coachDTO.getId())
        );
        coach.setTeamId(null);
        coachRepository.save(coach);
        return coachMapper.toDto(coach);
    }
}