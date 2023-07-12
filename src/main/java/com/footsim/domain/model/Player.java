package com.footsim.domain.model;


import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
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
    private Long id;

    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private PlayerPosition position;

    @Column(name = "status")
    private PlayerStatus status;


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", clubId=" + clubId +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", status=" + status +
                '}';
    }
}
