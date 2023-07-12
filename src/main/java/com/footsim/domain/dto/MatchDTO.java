package com.footsim.domain.dto;

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

    private String result;

    private LocalDateTime date;

    @Override
    public String toString() {
        return "MatchDTO{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", firstTeamId=" + firstTeamId +
                ", secondTeamId=" + secondTeamId +
                ", result='" + result + '\'' +
                ", date=" + date +
                '}';
    }
}