package com.footsim.repository;

import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link Player} entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    /**
     * Find all players in a distinct {@link Team}
     * @param id ID of a team
     * @return List of Players
     */

    List<Player> findByClubId(Long id);


    Integer countPlayerByClubIdAndStatus(Long clubId, PlayerStatus roster);

    Integer countPlayerByClubIdAndPositionAndStatus(Long clubId,
                                                    PlayerPosition playerPosition,
                                                    PlayerStatus roster);

    List<Player> findByClubIdAndStatus(Long homeTeamId, PlayerStatus roster);
}
