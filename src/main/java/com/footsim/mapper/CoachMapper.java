package com.footsim.mapper;

import com.footsim.domain.dto.CoachDTO;
import com.footsim.domain.model.Coach;
import org.mapstruct.Mapper;


/**
 * Mapper for the entity {@link Coach} and its DTO called {@link CoachDTO}.
 */

@Mapper(componentModel = "spring")
public interface CoachMapper extends EntityMapper<CoachDTO, Coach> {
    CoachDTO toDto(Coach p);

    Coach toEntity(CoachDTO p);
}

