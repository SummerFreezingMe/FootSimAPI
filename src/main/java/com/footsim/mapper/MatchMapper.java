package com.footsim.mapper;

import com.footsim.domain.dto.MatchDTO;
import com.footsim.domain.model.Match;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Match} and its DTO {@link MatchDTO}.
 */
@Mapper(componentModel = "spring")
public interface MatchMapper extends EntityMapper<MatchDTO, Match> {
    MatchDTO toDto(Match m);

    Match toEntity(MatchDTO m);
}
