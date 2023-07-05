package com.footsim.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A Event.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "ticket_limit")
    private String rating;

    @Column(name = "ticket_price")
    private String stadium;


    @Column(name = "description")
    private String description;

    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "image")
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Team)) {
            return false;
        }
        return id != null && id.equals(((Team) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
