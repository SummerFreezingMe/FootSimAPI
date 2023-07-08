package com.footsim.service.impl;

import com.footsim.domain.dto.TeamDTO;
import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Team;
import com.footsim.mapper.TeamMapper;
import com.footsim.repository.PlayerRepository;
import com.footsim.repository.TeamRepository;
import com.footsim.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Team}.
 */
@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TeamMapper teamMapper;


    public TeamServiceImpl(TeamRepository teamRepository,
                           PlayerRepository playerRepository,
                           TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public TeamDTO save(TeamDTO teamDTO) {
        log.debug("Request to save Team : {}", teamDTO);
        Team team = teamMapper.toEntity(teamDTO);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    @Override
    public TeamDTO update(TeamDTO teamDTO) {
        log.debug("Request to update Team : {}", teamDTO);
        Team team = teamMapper.toEntity(teamDTO);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    @Override
    public Optional<TeamDTO> partialUpdate(TeamDTO teamDTO) {
        log.debug("Request to partially update Team : {}", teamDTO);
        return teamRepository
                .findById(teamDTO.getId())
                .map(existingTeam -> {
                    teamMapper.partialUpdate(existingTeam, teamDTO);

                    return existingTeam;
                })
                .map(teamRepository::save)
                .map(teamMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDTO> findAll() {
        log.debug("Request to get all Teams");
        return teamRepository.findAll().stream().map(teamMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDTO findOne(Long id) {
        log.debug("Request to get Team : {}", id);
        return teamRepository.findById(id).map(teamMapper::toDto)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Team : {}", id);
        teamRepository.deleteById(id);
    }

    @Override
    public TeamDTO countTeamRating(Long id) {
        log.debug("Request to count Team rating: {}", id);
        Team team = teamRepository.findById(id).orElseThrow();
        List<Player> teamPlayers = playerRepository.findByClubId(id);
        Long newTeamRating = 0L;
        for (Player p : teamPlayers) {
            if (p.getStatus() == PlayerStatus.ROSTER) {
                newTeamRating += p.getRating();
            }
        }
        team.setRating(newTeamRating);
        teamRepository.save(team);
        return teamMapper.toDto(team);

    }

    @Override
    public boolean isRosterViable(Team team) {
        //todo: exception handling
        return playerRepository.countPlayerByClubIdAndPosition(team.getId(),
                PlayerPosition.GOALKEEPER) == 1 &&
                playerRepository.countPlayerByClubId(team.getId())==11;
    }

}
