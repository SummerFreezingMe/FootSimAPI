package com.footsim.domain.model;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A Match.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "match")
public class Match
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "season_id")
    private Long seasonId;

    @Column(name = "home_club_id")
    @NonNull
    private Long homeClubId;

    @Column(name = "away_club_id")
    @NonNull
    private Long awayClubId;

    @Column(name = "home_goals")
    @NonNull
    private Long homeGoals;

    @Column(name = "away_goals")
    @NonNull
    private Long AwayGoals;


    @Column(name = "date")
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equal(getId(), match.getId()) && Objects.equal(getSeasonId(), match.getSeasonId()) && Objects.equal(getHomeClubId(), match.getHomeClubId()) && Objects.equal(getAwayClubId(), match.getAwayClubId()) && Objects.equal(getHomeGoals(), match.getHomeGoals()) && Objects.equal(getAwayGoals(), match.getAwayGoals()) && Objects.equal(getDate(), match.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getSeasonId(), getHomeClubId(), getAwayClubId(), getDate());
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", seasonId=" + seasonId +
                ", homeClubId=" + homeClubId +
                ", awayClubId=" + awayClubId +
                ", homeGoals=" + homeGoals +
                ", AwayGoals=" + AwayGoals +
                ", date=" + date +
                '}';
    }
}
