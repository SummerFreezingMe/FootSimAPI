package com.footsim.service.impl;

import com.footsim.domain.dto.FoulDTO;
import com.footsim.domain.enumeration.FoulType;
import com.footsim.domain.enumeration.GoalType;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Foul;
import com.footsim.domain.model.Player;
import com.footsim.mapper.FoulMapper;
import com.footsim.repository.FoulRepository;
import com.footsim.service.FoulService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Foul}.
 */
@Service
@Transactional
public class FoulServiceImpl implements FoulService {

    private final FoulRepository foulRepository;
    private final GoalServiceImpl goalService;
    Random r = new Random();

    private final FoulMapper foulMapper;

    public FoulServiceImpl(FoulRepository foulRepository,
                           GoalServiceImpl goalService, FoulMapper foulMapper) {
        this.foulRepository = foulRepository;
        this.goalService = goalService;
        this.foulMapper = foulMapper;
    }

    @Override
    public FoulDTO save(FoulDTO FoulDTO) {
        Foul foul = foulMapper.toEntity(FoulDTO);
        foul = foulRepository.save(foul);
        return foulMapper.toDto(foul);
    }


    @Override
    public FoulDTO update(FoulDTO FoulDTO) {
        Foul foul = foulMapper.toEntity(FoulDTO);
        foul = foulRepository.save(foul);
        return foulMapper.toDto(foul);
    }

    @Override
    public Optional<FoulDTO> partialUpdate(FoulDTO FoulDTO) {
        return foulRepository
                .findById(FoulDTO.getId())
                .map(existingFoul -> {
                    foulMapper.partialUpdate(existingFoul, FoulDTO);

                    return existingFoul;
                })
                .map(foulRepository::save)
                .map(foulMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoulDTO> findAll() {
        return foulRepository.findAll().stream().map(foulMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public FoulDTO findOne(Long id) {
        return foulRepository.findById(id).map(foulMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Foul not found with id:" + id)
        );
    }

    @Override
    public void delete(Long id) {
        foulRepository.deleteById(id);
    }

    @Override
    public void generateFoul(List<Player> roster, Long matchId, short minute) {
        Player player = roster.get(r.nextInt(11));
        Foul foul = new Foul(0L, matchId, player.getId(),
                minute, Objects.requireNonNull(FoulType.getType(Math.random())));
        foulRepository.save(foul);
        if (checkForSendOffs(matchId, foul)) {
            player.setStatus(PlayerStatus.SENT_OFF);
            roster.remove(player);
        }
        if (Math.random() > 0.95) {
            goalService.generateGoal(roster, matchId, minute, GoalType.PENALTY);
        }
    }

    private boolean checkForSendOffs(Long matchId, Foul foul) {
        if (foul.getType().equals(FoulType.RED_CARD)) {
            return true;
        } else if (foul.getType().equals(FoulType.YELLOW_CARD)) {
            return foulRepository.countAllByPlayerIdAndMatchIdAndType(foul.getPlayerId(),
                    matchId, FoulType.YELLOW_CARD) > 1;
        } else return false;
    }

}