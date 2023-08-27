package com.footsim.repository;

import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Club;
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
     * Find all players in a distinct {@link Club}.
     *
     * @param id ID of a club
     * @return List of players
     */

    List<Player> findByClubId(Long id);


    /**
     * Count amount of players in a distinct {@link Club} of a distinct
     * {@link PlayerStatus}.
     *
     * @param clubId ID of a {@link Club}
     * @param status {@link PlayerStatus}
     * @return Amount of players
     */
    Integer countPlayerByClubIdAndStatus(Long clubId, PlayerStatus status);

    /**
     * Count amount of players in a distinct {@link Club} of a distinct
     * {@link PlayerStatus} and a distinct {@link PlayerPosition}.
     *
     * @param clubId         ID of a {@link Club}
     * @param playerPosition {@link PlayerPosition}
     * @param status         {@link PlayerStatus}
     * @return Amount of players
     */
    Integer countPlayerByClubIdAndPositionAndStatus(Long clubId,
                                                    PlayerPosition playerPosition,
                                                    PlayerStatus status);

    /**
     * Find players in a distinct {@link Club} of a distinct
     * {@link PlayerStatus}
     *
     * @param clubId ID of a {@link Club}
     * @param status {@link PlayerStatus}
     * @return List of players
     */
    List<Player> findByClubIdAndStatus(Long clubId, PlayerStatus status);
}