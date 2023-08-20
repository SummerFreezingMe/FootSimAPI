package com.footsim.service.impl;

import com.footsim.domain.dto.LeagueDTO;
import com.footsim.domain.model.League;
import com.footsim.mapper.LeagueMapper;
import com.footsim.repository.LeagueRepository;
import com.footsim.service.LeagueService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link League}.
 */
@Service
@Transactional
@AllArgsConstructor
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;

    @Override
    public LeagueDTO save(LeagueDTO LeagueDTO) {
        League league = leagueMapper.toEntity(LeagueDTO);
        league = leagueRepository.save(league);
        return leagueMapper.toDto(league);
    }

    @Override
    public LeagueDTO update(LeagueDTO leagueDTO) {
        League league = leagueMapper.toEntity(leagueDTO);
        league = leagueRepository.save(league);
        return leagueMapper.toDto(league);
    }

    @Override
    public Optional<LeagueDTO> partialUpdate(LeagueDTO LeagueDTO) {
        return leagueRepository
                .findById(LeagueDTO.getId())
                .map(existingGoal -> {
                    leagueMapper.partialUpdate(existingGoal, LeagueDTO);

                    return existingGoal;
                })
                .map(leagueRepository::save)
                .map(leagueMapper::toDto);
    }


    @Override
    public List<LeagueDTO> findAll() {
        return leagueRepository.findAll().stream().map(leagueMapper::toDto).collect(Collectors.toCollection(LinkedList::new));

    }

    @Override
    public LeagueDTO findOne(Long id) {
        return leagueRepository.findById(id).map(leagueMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("League not found with id:" + id)
        );
    }

    @Override
    public void delete(Long id) {
        leagueRepository.deleteById(id);
    }


}
