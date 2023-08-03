package com.footsim.mapper;

import com.footsim.domain.dto.SeasonDTO;
import com.footsim.domain.dto.SeasonStatDTO;
import com.footsim.domain.model.Season;
import com.footsim.domain.model.SeasonStat;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Season} and its DTO {@link SeasonDTO}.
 */
@Mapper(componentModel = "spring")
public interface SeasonStatMapper extends EntityMapper<SeasonStatDTO, SeasonStat> {
    SeasonStat toEntity(SeasonStatDTO s);

    SeasonStatDTO toDto(SeasonStat s);

}