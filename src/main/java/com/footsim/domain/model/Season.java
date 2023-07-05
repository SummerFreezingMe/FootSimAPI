package com.footsim.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A Venue.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "season")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Season implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "league_idd")
    private Long leagueId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_points")
    private Long points;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Season)) {
            return false;
        }
        return id != null && id.equals(((Season) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
