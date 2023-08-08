package com.footsim.repository;

import com.footsim.domain.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Coach} entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
}
