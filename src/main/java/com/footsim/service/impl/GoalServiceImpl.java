package com.footsim.service.impl;

import com.footsim.domain.dto.GoalDTO;
import com.footsim.domain.model.Goal;
import com.footsim.mapper.GoalMapper;
import com.footsim.repository.GoalRepository;
import com.footsim.service.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Goal}.
 */
@Service
@Transactional
public class GoalServiceImpl implements GoalService {

    private final Logger log = LoggerFactory.getLogger(GoalServiceImpl.class);

    private final GoalRepository goalRepository;


    private final GoalMapper goalMapper;

    public GoalServiceImpl(GoalRepository goalRepository,
                           GoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
    }

    @Override
    public GoalDTO save(GoalDTO GoalDTO) {
        log.debug("Request to save Goal : {}", GoalDTO);
        Goal goal = goalMapper.toEntity(GoalDTO);
        goal = goalRepository.save(goal);
        return goalMapper.toDto(goal);
    }


    @Override
    public GoalDTO update(GoalDTO GoalDTO) {
        log.debug("Request to update Goal : {}", GoalDTO);
        Goal goal = goalMapper.toEntity(GoalDTO);
        goal = goalRepository.save(goal);
        return goalMapper.toDto(goal);
    }

    @Override
    public Optional<GoalDTO> partialUpdate(GoalDTO GoalDTO) {
        log.debug("Request to partially update Goal : {}", GoalDTO);

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
        log.debug("Request to get all Goals");
        return goalRepository.findAll().stream().map(goalMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GoalDTO> findOne(Long id) {
        log.debug("Request to get Goal : {}", id);
        return goalRepository.findById(id).map(goalMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Goal : {}", id);
        goalRepository.deleteById(id);
    }

}
