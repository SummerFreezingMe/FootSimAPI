package com.footsim.service.impl;

import com.footsim.domain.dto.FoulDTO;
import com.footsim.domain.enumeration.FoulType;
import com.footsim.domain.model.Foul;
import com.footsim.domain.model.Player;
import com.footsim.mapper.FoulMapper;
import com.footsim.repository.FoulRepository;
import com.footsim.service.FoulService;
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
 * Service Implementation for managing {@link Foul}.
 */
@Service
@Transactional
public class FoulServiceImpl implements FoulService {

    private final Logger log = LoggerFactory.getLogger(FoulServiceImpl.class);

    private final FoulRepository foulRepository;

    Random r = new Random();

    private final FoulMapper foulMapper;

    public FoulServiceImpl(FoulRepository foulRepository,
                           FoulMapper foulMapper) {
        this.foulRepository = foulRepository;
        this.foulMapper = foulMapper;
    }

    @Override
    public FoulDTO save(FoulDTO FoulDTO) {
        log.debug("Request to save Foul : {}", FoulDTO);
        Foul foul = foulMapper.toEntity(FoulDTO);
        foul = foulRepository.save(foul);
        return foulMapper.toDto(foul);
    }


    @Override
    public FoulDTO update(FoulDTO FoulDTO) {
        log.debug("Request to update Foul : {}", FoulDTO);
        Foul foul = foulMapper.toEntity(FoulDTO);
        foul = foulRepository.save(foul);
        return foulMapper.toDto(foul);
    }

    @Override
    public Optional<FoulDTO> partialUpdate(FoulDTO FoulDTO) {
        log.debug("Request to partially update Foul : {}", FoulDTO);

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
        log.debug("Request to get all Fouls");
        return foulRepository.findAll().stream().map(foulMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FoulDTO> findOne(Long id) {
        log.debug("Request to get Foul : {}", id);
        return foulRepository.findById(id).map(foulMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Foul : {}", id);
        foulRepository.deleteById(id);
    }

    @Override
    public void generateFoul(List<Player> roster, Long id, short minute) {
        Foul foul = new Foul(0L, id, roster.get(r.nextInt(11)).getId(),
                 minute, FoulType.YELLOW_CARD);
        foulRepository.save(foul);
    }
}