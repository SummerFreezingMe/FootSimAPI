package com.footsim.domain.dto;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO representing a user, with only the public attributes.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDTO implements Serializable {

    private Long id;

    private Long leagueId;

    private Integer year;

    private Long teamId;

    private Long points;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonDTO seasonDTO = (SeasonDTO) o;
        return Objects.equal(getId(), seasonDTO.getId()) && Objects.equal(getLeagueId(), seasonDTO.getLeagueId()) && Objects.equal(getYear(), seasonDTO.getYear()) && Objects.equal(getTeamId(), seasonDTO.getTeamId()) && Objects.equal(getPoints(), seasonDTO.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getLeagueId(), getYear(), getTeamId(), getPoints());
    }

    @Override
    public String toString() {
        return "SeasonDTO{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", year=" + year +
                ", teamId=" + teamId +
                ", points=" + points +
                '}';
    }
}
