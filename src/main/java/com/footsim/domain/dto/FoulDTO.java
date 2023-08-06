package com.footsim.domain.dto;

import com.footsim.domain.enumeration.FoulType;
import com.footsim.domain.model.Foul;
import com.google.common.base.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * A DTO for the {@link Foul} entity.
 */
@Data
public class FoulDTO {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "matchId cannot be null")
    private Long matchId;

    @NotNull(message = "playerId cannot be null")
    private Long playerId;

    @NotNull(message = "minute cannot be null")
    private Short minute;

    @NotNull(message = "type cannot be null")
    private FoulType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoulDTO foulDTO = (FoulDTO) o;
        return Objects.equal(getId(), foulDTO.getId()) && Objects.equal(getMatchId(), foulDTO.getMatchId()) && Objects.equal(getPlayerId(), foulDTO.getPlayerId()) && Objects.equal(getMinute(), foulDTO.getMinute()) && getType() == foulDTO.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getMatchId(), getPlayerId(), getMinute(), getType());
    }

    @Override
    public String toString() {
        return "FoulDTO{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", playerId=" + playerId +
                ", minute=" + minute +
                ", type=" + type +
                '}';
    }
}
