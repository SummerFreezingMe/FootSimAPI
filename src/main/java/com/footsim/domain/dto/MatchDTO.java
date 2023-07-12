package com.footsim.domain.dto;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A DTO for the Response.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private Long id;

    private Long leagueId;

    private Long firstTeamId;

    private Long secondTeamId;

    private Long homeGoals;

    private Long AwayGoals;
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDTO matchDTO = (MatchDTO) o;
        return Objects.equal(getId(), matchDTO.getId()) && Objects.equal(getLeagueId(), matchDTO.getLeagueId()) && Objects.equal(getFirstTeamId(), matchDTO.getFirstTeamId()) && Objects.equal(getSecondTeamId(), matchDTO.getSecondTeamId()) && Objects.equal(getHomeGoals(), matchDTO.getHomeGoals()) && Objects.equal(getAwayGoals(), matchDTO.getAwayGoals()) && Objects.equal(getDate(), matchDTO.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getLeagueId(), getFirstTeamId(), getSecondTeamId(), getHomeGoals(), getAwayGoals(), getDate());
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", firstTeamId=" + firstTeamId +
                ", secondTeamId=" + secondTeamId +
                ", homeGoals=" + homeGoals +
                ", AwayGoals=" + AwayGoals +
                ", date=" + date +
                '}';
    }
}