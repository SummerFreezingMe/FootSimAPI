package com.footsim.domain.dto;


import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.domain.model.Player;
import com.google.common.base.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link Player} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class PlayerDTO implements Serializable {

    @NotNull(message = "id cannot be null")
    private Long id;

    private Long clubId;

    @NotNull(message = "rating cannot be null")
    private Long rating;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "position cannot be null")
    private PlayerPosition position;

    @NotNull(message = "status cannot be null")
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
