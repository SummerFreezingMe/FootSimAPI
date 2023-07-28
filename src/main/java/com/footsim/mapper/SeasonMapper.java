package com.footsim.mapper;

import com.footsim.domain.dto.SeasonDTO;
import com.footsim.domain.model.SeasonStat;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link SeasonStat} and its DTO {@link SeasonDTO}.
 */
@Mapper(componentModel = "spring")
public interface SeasonMapper extends EntityMapper<SeasonDTO, SeasonStat> {
    SeasonStat toEntity(SeasonDTO s);

    SeasonDTO toDto(SeasonStat s);


}
