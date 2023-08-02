package com.footsim.domain.model;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Season.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "season")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Season implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_points")
    private Long points;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equal(getId(), season.getId()) && Objects.equal(getLeagueId(), season.getLeagueId()) && Objects.equal(getYear(), season.getYear()) && Objects.equal(getTeamId(), season.getTeamId()) && Objects.equal(getPoints(), season.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getLeagueId(), getYear(), getTeamId(), getPoints());
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", year=" + year +
                ", teamId=" + teamId +
                ", points=" + points +
                '}';
    }
}
