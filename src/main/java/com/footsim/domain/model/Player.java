package com.footsim.domain.model;


import com.footsim.domain.enumeration.PlayerPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * A Event.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    Long id;

    @Column(name = "club_id")
    Long clubId;

    @Column(name = "rating")
    Long rating;

    @Column(name = "name")
    String name;

    @Column(name = "position")
    PlayerPosition position;

}
