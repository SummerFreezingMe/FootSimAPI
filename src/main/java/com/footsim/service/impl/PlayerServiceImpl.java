package com.footsim.service.impl;

import com.footsim.domain.dto.PlayerDTO;
import com.footsim.domain.dto.TransferDTO;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Team;
import com.footsim.mapper.PlayerMapper;
import com.footsim.repository.PlayerRepository;
import com.footsim.repository.TeamRepository;
import com.footsim.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Player}.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);

    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;

    private final PlayerMapper playerMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository,
                             PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public PlayerDTO save(PlayerDTO playerDTO) {
        log.debug("Request to save Player : {}", playerDTO);
        Player player = playerMapper.toEntity(playerDTO);
        player = playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    @Override
    public PlayerDTO update(PlayerDTO playerDTO) {
        log.debug("Request to update Player : {}", playerDTO);
        Player player = playerMapper.toEntity(playerDTO);
        player = playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    @Override
    public Optional<PlayerDTO> partialUpdate(PlayerDTO playerDTO) {
        log.debug("Request to partially update Player : {}", playerDTO);

        return playerRepository
                .findById(playerDTO.getId())
                .map(existingPlayer -> {
                    playerMapper.partialUpdate(existingPlayer, playerDTO);

                    return existingPlayer;
                })
                .map(playerRepository::save)
                .map(playerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDTO> findAll() {
        log.debug("Request to get all Players");
        return playerRepository.findAll().stream().map(playerMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDTO findOne(Long id) {
        log.debug("Request to get Player : {}", id);
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Player not found with id:" + id)
        );
        return playerMapper.toDto(player);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Player : {}", id);
        playerRepository.deleteById(id);
    }

    @Override
    public PlayerDTO switchStatus(Long id, PlayerStatus status) {
        log.debug("Request to switch role of a Player : {}", id);
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Player not found with id:" + id)
        );
            player.setStatus(status);
        return playerMapper.toDto(player);

    }

    @Override
    public PlayerDTO transferPlayer(TransferDTO transfer) {
        Player transferredPlayer = playerRepository.
                findById(transfer.getPlayerId()).orElseThrow(
                        () -> new EntityNotFoundException("Player not found with id:" + transfer.getPlayerId()));
        Team fromTeam = teamRepository.
                findById(transfer.getClubFromId()).orElseThrow(
                        () -> new EntityNotFoundException("Team not found with id:" + transfer.getClubFromId()));
        Team toTeam = teamRepository.
                findById(transfer.getClubToId()).orElseThrow(
                        () -> new EntityNotFoundException("Player not found with id:" + transfer.getClubToId()));
        transferredPlayer.setClubId(transfer.getClubToId());
        toTeam.setBalance(toTeam.getBalance() - transfer.getTransferFee());
        fromTeam.setBalance(fromTeam.getBalance() + transfer.getTransferFee());
        playerRepository.save(transferredPlayer);
        teamRepository.save(toTeam);
        teamRepository.save(fromTeam);
        return playerMapper.toDto(transferredPlayer);
    }
}
