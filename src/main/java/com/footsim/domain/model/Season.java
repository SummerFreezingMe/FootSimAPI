package com.footsim.domain.model;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Season statistics for each competing team.
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "season")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Season implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "league_id")
    @NonNull
    private Long leagueId;

    @Column(name = "season_year")
    @NonNull
    private String year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equal(getId(), season.getId()) && Objects.equal(getLeagueId(), season.getLeagueId()) && Objects.equal(getYear(), season.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getLeagueId(), getYear());
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", year=" + year +
                '}';
    }
}
