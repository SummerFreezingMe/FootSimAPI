package com.footsim.service.impl;

import com.footsim.domain.dto.GoalDTO;
import com.footsim.domain.dto.TopActionsDTO;
import com.footsim.domain.enumeration.GoalType;
import com.footsim.domain.model.Goal;
import com.footsim.domain.model.Player;
import com.footsim.mapper.GoalMapper;
import com.footsim.repository.GoalRepository;
import com.footsim.service.GoalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Goal}.
 */
@Service
@Transactional
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    Random r = new Random();
    private final GoalMapper goalMapper;

    public GoalServiceImpl(GoalRepository goalRepository,
                           GoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
    }

    @Override
    public GoalDTO save(GoalDTO GoalDTO) {
        Goal goal = goalMapper.toEntity(GoalDTO);
        goal = goalRepository.save(goal);
        return goalMapper.toDto(goal);
    }


    @Override
    public GoalDTO update(GoalDTO GoalDTO) {
        Goal goal = goalMapper.toEntity(GoalDTO);
        goal = goalRepository.save(goal);
        return goalMapper.toDto(goal);
    }

    @Override
    public Optional<GoalDTO> partialUpdate(GoalDTO GoalDTO) {
        return goalRepository
                .findById(GoalDTO.getId())
                .map(existingGoal -> {
                    goalMapper.partialUpdate(existingGoal, GoalDTO);

                    return existingGoal;
                })
                .map(goalRepository::save)
                .map(goalMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoalDTO> findAll() {
        return goalRepository.findAll().stream()
                .map(goalMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public GoalDTO findOne(Long id) {
        return goalRepository.findById(id).map(goalMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Goal not found with id:" + id)
        );
    }

    @Override
    public void delete(Long id) {
        goalRepository.deleteById(id);
    }

    @Override
    public void generateGoal(List<Player> roster, Long id, short minute, GoalType goalType) {
        Long scorerId = roster.get(r.nextInt(11)).getId();
        Long assistantId = null;
        if (goalType == GoalType.DEFAULT) {
            assistantId = roster.get(r.nextInt(11)).getId();
            if (assistantId.equals(scorerId)) {
                assistantId = null;
            }
        }
        Goal goal = new Goal(0L, id, scorerId,
                assistantId, minute, goalType);
        goalRepository.save(goal);
    }

    @Override
    public List<TopActionsDTO> displayTopScorers(Long seasonId) {
        return goalRepository.findTopScorers(seasonId);
    }

    @Override
    public List<TopActionsDTO> displayTopAssistants(Long seasonId) {
        return goalRepository.findTopAssistants(seasonId);
    }
}
