package com.footsim.domain.dto;


import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Player;
import com.google.common.base.Objects;
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

    private PlayerStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDTO playerDTO = (PlayerDTO) o;
        return Objects.equal(getId(), playerDTO.getId()) && Objects.equal(getClubId(), playerDTO.getClubId()) && Objects.equal(getRating(), playerDTO.getRating()) && Objects.equal(getName(), playerDTO.getName()) && getPosition() == playerDTO.getPosition() && getStatus() == playerDTO.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getClubId(), getRating(), getName(), getPosition());
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", clubId=" + clubId +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", status=" + status +
                '}';
    }
}
