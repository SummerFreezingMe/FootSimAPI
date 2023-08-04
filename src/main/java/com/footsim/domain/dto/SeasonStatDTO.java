package com.footsim.domain.dto;

import com.footsim.domain.model.SeasonStat;
import com.google.common.base.Objects;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link SeasonStat} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class SeasonStatDTO implements Serializable {

    private Long id;
    private Long seasonId;

    private Long teamId;

    private Long points;

    private Long wins;

    private Long draws;

    private Long defeats;

    private Long goalsScored;

    private Long goalsConceded;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonStatDTO seasonDTO = (SeasonStatDTO) o;
        return Objects.equal(getId(), seasonDTO.getId()) && Objects.equal(getSeasonId(), seasonDTO.getSeasonId())&& Objects.equal(getTeamId(), seasonDTO.getTeamId()) && Objects.equal(getPoints(), seasonDTO.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getSeasonId(), getTeamId(), getPoints());
    }

    @Override
    public String toString() {
        return "SeasonDTO{" +
                "id=" + id +
                ", leagueId=" + seasonId +
                ", teamId=" + teamId +
                ", points=" + points +
                ", wins=" + wins +
                ", draws=" + draws +
                ", defeats=" + defeats +
                ", goalsScored=" + goalsScored +
                ", goalsConceded=" + goalsConceded +
                '}';
    }
}
