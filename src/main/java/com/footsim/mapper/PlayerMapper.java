package com.footsim.mapper;

import com.footsim.domain.dto.PlayerDTO;
import com.footsim.domain.model.Player;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Player} and its DTO called {@link PlayerDTO}.
 */

@Mapper(componentModel = "spring")
public interface PlayerMapper extends EntityMapper<PlayerDTO, Player> {
    PlayerDTO toDto(Player p);

    Player toEntity(PlayerDTO p);
}

