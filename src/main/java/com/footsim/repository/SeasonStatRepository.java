package com.footsim.repository;

import com.footsim.domain.model.SeasonStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link SeasonStat} entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeasonStatRepository extends JpaRepository<SeasonStat, Long> {

    Optional<SeasonStat> findBySeasonIdAndTeamId(Long seasonId, Long teamId);

    long countAllBySeasonId(Long seasonId);
}
