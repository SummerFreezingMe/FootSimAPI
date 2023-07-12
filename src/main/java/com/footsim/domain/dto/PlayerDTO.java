package com.footsim.domain.dto;


import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link Player} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO implements Serializable {

    private Long id;

    private Long clubId;

    private Long rating;

    private String name;

    private PlayerPosition position;

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", clubId=" + clubId +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
