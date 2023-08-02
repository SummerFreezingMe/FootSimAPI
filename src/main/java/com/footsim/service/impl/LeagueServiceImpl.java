package com.footsim.service.impl;

import com.footsim.domain.dto.LeagueDTO;
import com.footsim.domain.model.League;
import com.footsim.mapper.LeagueMapper;
import com.footsim.repository.LeagueRepository;
import com.footsim.service.LeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link League}.
 */
@Service
@Transactional
public class LeagueServiceImpl implements LeagueService {

    private final Logger log = LoggerFactory.getLogger(LeagueServiceImpl.class);

    private final LeagueRepository leagueRepository;

    private final LeagueMapper leagueMapper;

    public LeagueServiceImpl(LeagueRepository leagueRepository, LeagueMapper leagueMapper) {
        this.leagueRepository = leagueRepository;
        this.leagueMapper = leagueMapper;
    }


    @Override
    public LeagueDTO save(LeagueDTO LeagueDTO) {
        log.debug("Request to save League : {}", LeagueDTO);
        League league = leagueMapper.toEntity(LeagueDTO);
        league = leagueRepository.save(league);
        return leagueMapper.toDto(league);
    }

    @Override
    public LeagueDTO update(LeagueDTO leagueDTO) {
        log.debug("Request to update League : {}", leagueDTO);
        League league = leagueMapper.toEntity(leagueDTO);
        league = leagueRepository.save(league);
        return leagueMapper.toDto(league);
    }

    @Override
    public Optional<LeagueDTO> partialUpdate(LeagueDTO LeagueDTO) {
        return Optional.empty();
    }

    @Override
    public List<LeagueDTO> findAll() {
        return null;
    }

    @Override
    public Optional<LeagueDTO> findOne(Long id) {
        log.debug("Request to get League : {}", id);
        return leagueRepository.findById(id).map(leagueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete League : {}", id);
        leagueRepository.deleteById(id);
    }


}
