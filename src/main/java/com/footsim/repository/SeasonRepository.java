package com.footsim.repository;

import com.footsim.domain.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Season} entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Long countAllByLeagueIdAndYear(Long leagueId, Integer year);
}
