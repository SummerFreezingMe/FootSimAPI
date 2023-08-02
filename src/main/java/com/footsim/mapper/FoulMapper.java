package com.footsim.mapper;

import com.footsim.domain.dto.FoulDTO;
import com.footsim.domain.model.Foul;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Foul} and its DTO {@link FoulDTO}.
 */
@Mapper(componentModel = "spring")
public interface FoulMapper extends EntityMapper<FoulDTO, Foul> {
   FoulDTO toDto(Foul f);

    Foul toEntity(FoulDTO f);
}
