package com.footsim.domain.model;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A Match.
 */
@Data
@Entity
@Table(name = "match")
public class Match
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "season_id")
    private Long seasonId;

    @Column(name = "home_team_id")
    private Long homeTeamId;

    @Column(name = "away_team_id")
    private Long awayTeamId;

    @Column(name = "home_goals")
    private Long homeGoals;

    @Column(name = "away_goals")
    private Long AwayGoals;


    @Column(name = "date")
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equal(getId(), match.getId()) && Objects.equal(getSeasonId(), match.getSeasonId()) && Objects.equal(getHomeTeamId(), match.getHomeTeamId()) && Objects.equal(getAwayTeamId(), match.getAwayTeamId()) && Objects.equal(getHomeGoals(), match.getHomeGoals()) && Objects.equal(getAwayGoals(), match.getAwayGoals()) && Objects.equal(getDate(), match.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getSeasonId(), getHomeTeamId(), getAwayTeamId(), getDate());
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", seasonId=" + seasonId +
                ", homeTeamId=" + homeTeamId +
                ", awayTeamId=" + awayTeamId +
                ", homeGoals=" + homeGoals +
                ", AwayGoals=" + AwayGoals +
                ", date=" + date +
                '}';
    }
}
