package com.footsim.service.impl;

import com.footsim.domain.dto.ClubDTO;
import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Coach;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Club;
import com.footsim.mapper.ClubMapper;
import com.footsim.repository.CoachRepository;
import com.footsim.repository.PlayerRepository;
import com.footsim.repository.ClubRepository;
import com.footsim.service.ClubService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(ClubServiceImpl.class);
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    private final CoachRepository coachRepository;
    private final ClubMapper clubMapper;

    @Override
    public ClubDTO save(ClubDTO clubDTO) {
        log.debug("Request to save Club : {}", clubDTO);
        Club club = clubMapper.toEntity(clubDTO);
        club = clubRepository.save(club);
        return clubMapper.toDto(club);
    }

    @Override
    public ClubDTO update(ClubDTO clubDTO) {
        log.debug("Request to update Club : {}", clubDTO);
        Club club = clubMapper.toEntity(clubDTO);
        club = clubRepository.save(club);
        return clubMapper.toDto(club);
    }

    @Override
    public Optional<ClubDTO> partialUpdate(ClubDTO clubDTO) {
        log.debug("Request to partially update Club : {}", clubDTO);
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
        log.debug("Request to get all Clubs");
        return clubRepository.findAll().stream().map(clubMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public ClubDTO findOne(Long id) {
        log.debug("Request to get Club : {}", id);
        return clubRepository.findById(id).map(clubMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Season not found with id:" + id)
        );
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Club : {}", id);
        clubRepository.deleteById(id);
    }

    @Override
    public ClubDTO countClubRating(Long id) {
        log.debug("Request to count Club rating: {}", id);
        Club club = clubRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Club not found with id:" + id));
        List<Player> clubPlayers = playerRepository.findByClubId(id);
        Coach coach = coachRepository.findByClubId(id);
        int newClubRating = coach.getRating()*2;
        for (Player p : clubPlayers) {
            if (p.getStatus() == PlayerStatus.ROSTER) {
                newClubRating += p.getRating();
            }
        }
        club.setRating(newClubRating);
        clubRepository.save(club);
        return clubMapper.toDto(club);

    }

    @Override
    public boolean isRosterViable(Club club) {
        log.debug("Request to check for roster viability Club : {}", club.getId());
        return playerRepository.countPlayerByClubIdAndPositionAndStatus(
                club.getId(), PlayerPosition.GOALKEEPER, PlayerStatus.ROSTER) == 1 &&
                playerRepository.countPlayerByClubIdAndStatus(club.getId(),
                        PlayerStatus.ROSTER) == 11;
    }

}
