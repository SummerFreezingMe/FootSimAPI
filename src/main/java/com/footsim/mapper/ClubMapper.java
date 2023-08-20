package com.footsim.mapper;


import com.footsim.domain.dto.ClubDTO;
import com.footsim.domain.model.Club;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Club} and its DTO {@link ClubDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClubMapper extends EntityMapper<ClubDTO, Club> {
    Club toEntity(ClubDTO t);

    ClubDTO toDto(Club t);


}
