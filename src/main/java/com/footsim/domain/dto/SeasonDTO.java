package com.footsim.domain.dto;

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
