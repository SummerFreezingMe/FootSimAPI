package com.footsim.service.impl;

import com.footsim.domain.dto.ClubDTO;
import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Club;
import com.footsim.domain.model.Coach;
import com.footsim.domain.model.Player;
import com.footsim.mapper.ClubMapper;
import com.footsim.repository.ClubRepository;
import com.footsim.repository.CoachRepository;
import com.footsim.repository.PlayerRepository;
import com.footsim.service.ClubService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Club}.
 */
@Service
@Transactional
@AllArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;
    private final CoachRepository coachRepository;
    private final ClubMapper clubMapper;

    @Override
    public ClubDTO save(ClubDTO clubDTO) {
        Club club = clubMapper.toEntity(clubDTO);
        club = clubRepository.save(club);
        return clubMapper.toDto(club);
    }

    @Override
    public ClubDTO update(ClubDTO clubDTO) {
        Club club = clubMapper.toEntity(clubDTO);
        club = clubRepository.save(club);
        return clubMapper.toDto(club);
    }

    @Override
    public Optional<ClubDTO> partialUpdate(ClubDTO clubDTO) {
        return clubRepository
                .findById(clubDTO.getId())
                .map(existingClub -> {
                    clubMapper.partialUpdate(existingClub, clubDTO);

                    return existingClub;
                })
                .map(clubRepository::save)
                .map(clubMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClubDTO> findAll() {
        return clubRepository.findAll().stream().map(clubMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public ClubDTO findOne(Long id) {
        return clubRepository.findById(id).map(clubMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Season not found with id:" + id)
        );
    }

    @Override
    public void delete(Long id) {
        clubRepository.deleteById(id);
    }

    @Override
    public ClubDTO countClubRating(Long id) {
        Club club = clubRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Club not found with id:" + id));
        List<Player> clubPlayers = playerRepository.findByClubId(id);
        Coach coach = coachRepository.findByClubId(id);

        int newClubRating = coach.getRating() * 2 + clubPlayers.stream()
                .filter(p -> p.getStatus() == PlayerStatus.ROSTER)
                .mapToInt(Player::getRating)
                .sum();

        club.setRating(newClubRating);
        clubRepository.save(club);
        return clubMapper.toDto(club);

    }

    @Override
    public boolean isRosterViable(Club club) {
       return playerRepository.countPlayerByClubIdAndPositionAndStatus(
                club.getId(), PlayerPosition.GOALKEEPER, PlayerStatus.ROSTER) == 1 &&
                playerRepository.countPlayerByClubIdAndStatus(club.getId(),
                        PlayerStatus.ROSTER) == 11;
    }

}
