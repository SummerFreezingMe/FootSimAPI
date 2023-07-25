package com.footsim.repository;

import com.footsim.domain.dto.TopActionsDTO;
import com.footsim.domain.model.Goal;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link Goal} entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    /**
     * @param seasonId id of a {@link Season}
     * @return List of id's of a {@link Player} and amount of their goals
     */
    @Query(value = "select player.id, count(goal.author_id)" +
            "            from player join goal" +
            "            on player.id=goal.author_id" +
            " group by player.id" +
            "order by count(goal.author_id) desc ",nativeQuery = true)
    List<TopActionsDTO> findTopScorers(Long seasonId);
    /**
     * @param seasonId id of a {@link Season}
     * @return List of id's of a {@link Player} and amount of their assists
     */
    @Query(value = "select player.id, count(goal.assist_id)" +
            "            from player join goal" +
            "            on player.id=goal.author_id" +
            " group by player.id" +
            " order by count(goal.assist_id) desc ",nativeQuery = true)
    List<TopActionsDTO> findTopAssistants(Long seasonId);
}
