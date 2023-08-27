package com.footsim.domain.model;


import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Club.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "club")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Club implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    @NonNull
    private String name;


    @Column(name = "rating")
    @NonNull
    private Integer rating;

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
        Club club = (Club) o;
        return Objects.equal(getId(), club.getId()) && Objects.equal(getName(), club.getName()) &&
                Objects.equal(getRating(), club.getRating()) && Objects.equal(getStadium(), club.getStadium()) &&
                Objects.equal(getDescription(), club.getDescription()) && Objects.equal(getLeagueId(), club.getLeagueId()) &&
                Objects.equal(getImage(), club.getImage()) && Objects.equal(getBalance(), club.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getRating(), getStadium(), getDescription(), getLeagueId(), getImage(), getBalance());
    }

    @Override
    public String toString() {
        return "Club{" +
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
