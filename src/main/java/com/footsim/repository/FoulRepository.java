package com.footsim.repository;

import com.footsim.domain.enumeration.FoulType;
import com.footsim.domain.model.Foul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Foul} entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoulRepository extends JpaRepository<Foul, Long> {
    Long countAllByPlayerIdAndMatchIdAndType(Long playerId, Long matchId, FoulType ft);
}
