package com.footsim.domain.dto;


import com.footsim.domain.enumeration.GoalType;
import com.footsim.domain.model.Goal;
import com.google.common.base.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link Goal} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class GoalDTO implements Serializable {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "matchId cannot be null")
    private Long matchId;

    @NotNull(message = "authorId cannot be null")
    private Long authorId;

    private Long assistId;

    @NotNull(message = "minute cannot be null")
    private Short minute;

    @NotNull(message = "type cannot be null")
    private GoalType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalDTO goalDTO = (GoalDTO) o;
        return Objects.equal(getId(), goalDTO.getId()) && Objects.equal(getMatchId(), goalDTO.getMatchId()) && Objects.equal(getAuthorId(), goalDTO.getAuthorId()) && Objects.equal(getAssistId(), goalDTO.getAssistId()) && Objects.equal(getMinute(), goalDTO.getMinute()) && getType() == goalDTO.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getMatchId(), getAuthorId(), getAssistId(), getMinute());
    }

    @Override
    public String toString() {
        return "GoalDTO{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", authorId=" + authorId +
                ", assistId=" + assistId +
                ", minute=" + minute +
                ", type=" + type +
                '}';
    }
}
