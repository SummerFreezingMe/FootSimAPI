package com.footsim.service.impl;

import com.footsim.domain.dto.PlayerDTO;
import com.footsim.domain.dto.TransferDTO;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Club;
import com.footsim.domain.model.Player;
import com.footsim.mapper.PlayerMapper;
import com.footsim.repository.ClubRepository;
import com.footsim.repository.PlayerRepository;
import com.footsim.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDTO save(PlayerDTO playerDTO) {
        Player player = playerMapper.toEntity(playerDTO);
        player = playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    @Override
    public PlayerDTO update(PlayerDTO playerDTO) {
        Player player = playerMapper.toEntity(playerDTO);
        player = playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    @Override
    public Optional<PlayerDTO> partialUpdate(PlayerDTO playerDTO) {
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
        return playerRepository.findAll().stream().map(playerMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDTO findOne(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Player not found with id:" + id)
        );
        return playerMapper.toDto(player);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public PlayerDTO switchStatus(Long id, PlayerStatus status) {
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Player not found with id:" + id)
        );
            player.setStatus(status);
            playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    @Override
    public PlayerDTO transferPlayer(TransferDTO transfer) {
        Player transferredPlayer = playerRepository.
                findById(transfer.getPersonId()).orElseThrow(
                        () -> new EntityNotFoundException("Player not found with id:" + transfer.getPersonId()));
        Club fromClub = clubRepository.
                findById(transfer.getClubFromId()).orElseThrow(
                        () -> new EntityNotFoundException("Club not found with id:" + transfer.getClubFromId()));
        Club toClub = clubRepository.
                findById(transfer.getClubToId()).orElseThrow(
                        () -> new EntityNotFoundException("Player not found with id:" + transfer.getClubToId()));
        transferredPlayer.setClubId(transfer.getClubToId());
        toClub.setBalance(toClub.getBalance() - transfer.getTransferFee());
        fromClub.setBalance(fromClub.getBalance() + transfer.getTransferFee());
        playerRepository.save(transferredPlayer);
        clubRepository.save(toClub);
        clubRepository.save(fromClub);
        return playerMapper.toDto(transferredPlayer);
    }

    @Override
    public PlayerDTO releasePlayer(PlayerDTO playerDTO) {
        Player player = playerRepository.findById(playerDTO.getId()).orElseThrow(
                () -> new EntityNotFoundException("Player not found with id:" + playerDTO.getId())
        );
        player.setClubId(null);
        playerRepository.save(player);
        return playerMapper.toDto(player);
    }
}
