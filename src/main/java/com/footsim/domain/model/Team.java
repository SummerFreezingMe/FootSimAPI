package com.footsim.domain.model;


import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Team.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "team")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Team implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    @NonNull
    private String name;


    @Column(name = "rating")
    @NonNull
    private Long rating;

    @Column(name = "stadium")
    private String stadium;


    @Column(name = "description")
    private String description;

    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "image")
    private String image;

    @Column(name = "balance")
    @NonNull
    private Long balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equal(getId(), team.getId()) && Objects.equal(getName(), team.getName()) && Objects.equal(getRating(), team.getRating()) && Objects.equal(getStadium(), team.getStadium()) && Objects.equal(getDescription(), team.getDescription()) && Objects.equal(getLeagueId(), team.getLeagueId()) && Objects.equal(getImage(), team.getImage()) && Objects.equal(getBalance(), team.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getRating(), getStadium(), getDescription(), getLeagueId(), getImage(), getBalance());
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", stadium='" + stadium + '\'' +
                ", description='" + description + '\'' +
                ", leagueId=" + leagueId +
                ", image='" + image + '\'' +
                ", balance=" + balance +
                '}';
    }
}
