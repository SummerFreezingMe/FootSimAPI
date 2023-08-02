package com.footsim.domain.dto;

import com.footsim.domain.enumeration.FoulType;
import com.footsim.domain.model.Foul;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link Foul} entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoulDTO {

    private Long id;

    private Long matchId;

    private Long playerId;

    private Short minute;

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
