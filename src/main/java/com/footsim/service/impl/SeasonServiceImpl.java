package com.footsim.service.impl;

import com.footsim.domain.dto.SeasonDTO;
import com.footsim.domain.model.Season;
import com.footsim.mapper.SeasonMapper;
import com.footsim.repository.SeasonRepository;
import com.footsim.service.SeasonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing {@link Season}.
 */
@Service
@Transactional
@AllArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    @Override
    public SeasonDTO save(SeasonDTO seasonDTO) {
        Season season = seasonMapper.toEntity(seasonDTO);
        season = seasonRepository.save(season);
        return seasonMapper.toDto(season);
    }

    @Override
    public SeasonDTO update(SeasonDTO seasonDTO) {
        Season season = seasonMapper.toEntity(seasonDTO);
        season = seasonRepository.save(season);
        return seasonMapper.toDto(season);
    }

    @Override
    public Optional<SeasonDTO> partialUpdate(SeasonDTO seasonDTO) {
        return seasonRepository
                .findById(seasonDTO.getId())
                .map(existingSeason -> {
                    seasonMapper.partialUpdate(existingSeason, seasonDTO);

                    return existingSeason;
                })
                .map(seasonRepository::save)
                .map(seasonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeasonDTO> findAll() {
        return seasonRepository.findAll().stream()
                .map(seasonMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public SeasonDTO findOne(Long id) {
        return seasonRepository.findById(id).map(seasonMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Season not found with id:" + id)
        );
    }

    @Override
    public void delete(Long id) {
        seasonRepository.deleteById(id);
    }

}
