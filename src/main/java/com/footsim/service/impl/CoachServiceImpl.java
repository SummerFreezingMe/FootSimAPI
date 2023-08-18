package com.footsim.service.impl;

import com.footsim.domain.dto.CoachDTO;
import com.footsim.domain.dto.TransferDTO;
import com.footsim.domain.model.Club;
import com.footsim.domain.model.Coach;
import com.footsim.domain.model.Player;
import com.footsim.mapper.CoachMapper;
import com.footsim.repository.ClubRepository;
import com.footsim.repository.CoachRepository;
import com.footsim.repository.PlayerRepository;
import com.footsim.service.CoachService;
import jakarta.persistence.EntityNotFoundException;
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
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final CoachMapper coachMapper;

    Random r = new Random();

    public CoachServiceImpl(CoachRepository coachRepository, PlayerRepository playerRepository, ClubRepository clubRepository, CoachMapper coachMapper) {
        this.coachRepository = coachRepository;
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.coachMapper = coachMapper;
    }

    @Override
    public CoachDTO save(CoachDTO coachDTO) {
        Coach coach = coachMapper.toEntity(coachDTO);
        coach = coachRepository.save(coach);
        return coachMapper.toDto(coach);
    }

    @Override
    public CoachDTO update(CoachDTO coachDTO) {
        Coach coach = coachMapper.toEntity(coachDTO);
        coach = coachRepository.save(coach);
        return coachMapper.toDto(coach);
    }

    @Override
    public Optional<CoachDTO> partialUpdate(CoachDTO coachDTO) {
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
        return coachRepository.findAll().stream().map(coachMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public CoachDTO findOne(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Coach not found with id:" + id)
        );
        return coachMapper.toDto(coach);
    }

    @Override
    public void delete(Long id) {
        coachRepository.deleteById(id);
    }

    @Override
    public CoachDTO transferCoach(TransferDTO transfer) {
        Coach transferredCoach = coachRepository.
                findById(transfer.getPersonId()).orElseThrow(
                        () -> new EntityNotFoundException("Coach not found with id:" + transfer.getPersonId()));
        Club fromClub = clubRepository.
                findById(transfer.getClubFromId()).orElseThrow(
                        () -> new EntityNotFoundException("Club not found with id:" + transfer.getClubFromId()));
        Club toClub = clubRepository.
                findById(transfer.getClubToId()).orElseThrow(
                        () -> new EntityNotFoundException("Coach not found with id:" + transfer.getClubToId()));
        transferredCoach.setClubId(transfer.getClubToId());
        toClub.setBalance(toClub.getBalance() - transfer.getTransferFee());
        fromClub.setBalance(fromClub.getBalance() + transfer.getTransferFee());
        coachRepository.save(transferredCoach);
        clubRepository.save(toClub);
        clubRepository.save(fromClub);
        return coachMapper.toDto(transferredCoach);
    }

    @Override
    public CoachDTO retireToCoaching(Player player) {
        Coach newCoach = new Coach();
        newCoach.setName(player.getName());
        newCoach.setRating(r.nextInt(200));
        coachRepository.save(newCoach);
        playerRepository.delete(player);
        return coachMapper.toDto(newCoach);
    }

    @Override
    public CoachDTO releaseCoach(CoachDTO coachDTO) {
        Coach coach = coachRepository.findById(coachDTO.getId()).orElseThrow(
                () -> new EntityNotFoundException("Coach not found with id:" + coachDTO.getId())
        );
        coach.setClubId(null);
        coachRepository.save(coach);
        return coachMapper.toDto(coach);
    }
}