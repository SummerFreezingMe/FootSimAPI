package com.footsim.mapper;

import com.footsim.domain.dto.SeasonDTO;
import com.footsim.domain.model.Season;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Season} and its DTO {@link SeasonDTO}.
 */
@Mapper(componentModel = "spring")
public interface SeasonMapper extends EntityMapper<SeasonDTO, Season> {
    Season toEntity(SeasonDTO s);

    SeasonDTO toDto(Season s);


}
