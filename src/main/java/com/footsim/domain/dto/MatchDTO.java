package com.footsim.domain.dto;

import com.footsim.domain.model.Match;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link Match} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private Long id;

    private Long leagueId;

    private Long homeTeamId;

    private Long awayTeamId;

    private Long homeGoals;

    private Long AwayGoals;
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDTO matchDTO = (MatchDTO) o;
        return Objects.equal(getId(), matchDTO.getId()) && Objects.equal(getLeagueId(), matchDTO.getLeagueId()) &&
                Objects.equal(getHomeTeamId(), matchDTO.getHomeTeamId()) &&
                Objects.equal(getAwayTeamId(), matchDTO.getAwayTeamId()) &&
                Objects.equal(getHomeGoals(), matchDTO.getHomeGoals()) &&
                Objects.equal(getAwayGoals(), matchDTO.getAwayGoals()) &&
                Objects.equal(getDate(), matchDTO.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getLeagueId(), getHomeTeamId(), getAwayTeamId(), getHomeGoals(), getAwayGoals(), getDate());
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", homeTeamId=" + homeTeamId +
                ", awayTeamId=" + awayTeamId +
                ", homeGoals=" + homeGoals +
                ", AwayGoals=" + AwayGoals +
                ", date=" + date +
                '}';
    }
}