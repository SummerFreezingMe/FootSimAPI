package com.footsim.repository;

import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link Player} entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByClubId(Long id);

    Integer countPlayerByClubId(Long clubId);

    Integer countPlayerByClubIdAndPosition(Long clubId, PlayerPosition playerPosition);

    List<Player> findByClubIdAndStatus(Long homeTeamId, PlayerStatus roster);
}
