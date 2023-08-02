package com.footsim.mapper;

import com.footsim.domain.dto.LeagueDTO;
import com.footsim.domain.model.League;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link League} and its DTO {@link LeagueDTO}.
 */
@Mapper(componentModel = "spring")
public interface LeagueMapper extends EntityMapper<LeagueDTO, League> {
    LeagueDTO toDto(League l);

    League toEntity(LeagueDTO l);
}

