package com.footsim.mapper;


import com.footsim.domain.dto.TeamDTO;
import com.footsim.domain.model.Team;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Team} and its DTO {@link TeamDTO}.
 */
@Mapper(componentModel = "spring")
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {
    Team toEntity(TeamDTO t);

    TeamDTO toDto(Team t);


}
