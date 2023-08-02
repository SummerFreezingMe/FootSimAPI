package com.footsim.repository;

import com.footsim.domain.enumeration.FoulType;
import com.footsim.domain.model.Foul;
import com.footsim.domain.model.Match;
import com.footsim.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Foul} entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoulRepository extends JpaRepository<Foul, Long> {
    /**
     * Counting amount of fouls with a distinct (for example, fouls on a yellow card)
     * type of {@link Player} in a single {@link Match}.
     * @param playerId Id of a player
     * @param matchId Id of a match
     * @param ft Foul type
     * @return Amount of fouls
     */
    Long countAllByPlayerIdAndMatchIdAndType(Long playerId, Long matchId, FoulType ft);
}
