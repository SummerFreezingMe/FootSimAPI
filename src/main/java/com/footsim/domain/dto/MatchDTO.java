package com.footsim.domain.dto;

import com.footsim.domain.model.Match;
import com.google.common.base.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link Match} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class MatchDTO {

    @NotNull(message = "id cannot be null")
    private Long id;

    private Long seasonId;

    @NotNull(message = "homeClubId cannot be null")
    private Long homeClubId;

    @NotNull(message = "awayClubId cannot be null")
    private Long awayClubId;

    @NotNull(message = "homeGoals cannot be null")
    private Long homeGoals;

    @NotNull(message = "AwayGoals cannot be null")
    private Long AwayGoals;

    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDTO matchDTO = (MatchDTO) o;
        return Objects.equal(getId(), matchDTO.getId()) && Objects.equal(getSeasonId(), matchDTO.getSeasonId()) &&
                Objects.equal(getHomeClubId(), matchDTO.getHomeClubId()) &&
                Objects.equal(getAwayClubId(), matchDTO.getAwayClubId()) &&
                Objects.equal(getHomeGoals(), matchDTO.getHomeGoals()) &&
                Objects.equal(getAwayGoals(), matchDTO.getAwayGoals()) &&
                Objects.equal(getDate(), matchDTO.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getSeasonId(), getHomeClubId(), getAwayClubId(), getHomeGoals(), getAwayGoals(), getDate());
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "id=" + id +
                ", seasonId=" + seasonId +
                ", homeClubId=" + homeClubId +
                ", awayClubId=" + awayClubId +
                ", homeGoals=" + homeGoals +
                ", AwayGoals=" + AwayGoals +
                ", date=" + date +
                '}';
    }
}