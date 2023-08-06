package com.footsim.domain.model;


import com.footsim.domain.enumeration.PlayerPosition;
import com.footsim.domain.enumeration.PlayerStatus;
import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;


/**
 * A Player.
 */

@Data
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
    @NonNull
    private Long rating;

    @Column(name = "name")
    @NonNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    @NonNull
    private PlayerPosition position;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NonNull
    private PlayerStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equal(getId(), player.getId()) && Objects.equal(getClubId(), player.getClubId()) && Objects.equal(getRating(), player.getRating()) && Objects.equal(getName(), player.getName()) && getPosition() == player.getPosition() && getStatus() == player.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getClubId(), getRating(), getName());
    }

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
