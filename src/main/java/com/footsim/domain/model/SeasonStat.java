package com.footsim.domain.model;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Season statistics for each competing club.
 */
@Data
@Entity
@Table(name = "season_stat")
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SeasonStat implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "season_id")
    @NonNull
    private Long seasonId;


    @Column(name = "club_id")
    @NonNull
    private Long clubId;

    @Column(name = "club_points")
    @NonNull
    private Long points;

    //todo: settle for "long" now, figure it out later
    @Column(name = "wins")
    @NonNull
    private Long wins;

    @Column(name = "draws")
    @NonNull
    private Long draws;

    @Column(name = "defeats")
    @NonNull
    private Long defeats;

    @Column(name = "goals_scored")
    @NonNull
    private Long goalsScored;

    @Column(name = "goals_conceded")
    @NonNull
    private Long goalsConceded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonStat that = (SeasonStat) o;
        return Objects.equal(getId(), that.getId()) && Objects.equal(getSeasonId(), that.getSeasonId()) &&
                Objects.equal(getClubId(), that.getClubId()) && Objects.equal(getPoints(), that.getPoints()) &&
                Objects.equal(getWins(), that.getWins()) && Objects.equal(getDraws(), that.getDraws()) &&
                Objects.equal(getDefeats(), that.getDefeats()) && Objects.equal(getGoalsScored(), that.getGoalsScored()) &&
                Objects.equal(getGoalsConceded(), that.getGoalsConceded());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getSeasonId(), getClubId(), getPoints());
    }

    @Override
    public String toString() {
        return "SeasonStat{" +
                "id=" + id +
                ", seasonId=" + seasonId +
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
