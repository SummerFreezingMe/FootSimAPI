package com.footsim.domain.dto;

import com.footsim.domain.model.SeasonStat;
import com.google.common.base.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link SeasonStat} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class SeasonStatDTO implements Serializable {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "seasonId cannot be null")
    private Long seasonId;

    @NotNull(message = "clubId cannot be null")
    private Long clubId;

    @NotNull(message = "points cannot be null")
    private Long points;

    @NotNull(message = "wins cannot be null")
    private Long wins;

    @NotNull(message = "draws cannot be null")
    private Long draws;

    @NotNull(message = "defeats cannot be null")
    private Long defeats;

    @NotNull(message = "goalsScored cannot be null")
    private Long goalsScored;

    @NotNull(message = "goalsConceded cannot be null")
    private Long goalsConceded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonStatDTO seasonDTO = (SeasonStatDTO) o;
        return Objects.equal(getId(), seasonDTO.getId()) && Objects.equal(getSeasonId(), seasonDTO.getSeasonId())&&
                Objects.equal(getClubId(), seasonDTO.getClubId()) && Objects.equal(getPoints(), seasonDTO.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getSeasonId(), getClubId(), getPoints());
    }

    @Override
    public String toString() {
        return "SeasonDTO{" +
                "id=" + id +
                ", leagueId=" + seasonId +
                ", clubId=" + clubId +
                ", points=" + points +
                ", wins=" + wins +
                ", draws=" + draws +
                ", defeats=" + defeats +
                ", goalsScored=" + goalsScored +
                ", goalsConceded=" + goalsConceded +
                '}';
    }
}
