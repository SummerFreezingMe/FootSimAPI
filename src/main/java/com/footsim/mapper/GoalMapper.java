package com.footsim.mapper;

import com.footsim.domain.dto.GoalDTO;
import com.footsim.domain.model.Goal;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Goal} and its DTO {@link GoalDTO}.
 */
@Mapper(componentModel = "spring")
public interface GoalMapper extends EntityMapper<GoalDTO, Goal> {
    GoalDTO toDto(Goal g);

    Goal toEntity(GoalDTO g);
}
