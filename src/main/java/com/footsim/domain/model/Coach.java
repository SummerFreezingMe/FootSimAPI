package com.footsim.domain.model;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Coach.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "coach")
public class Coach  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "rating")
    @NonNull
    private Long rating;

    @Column(name = "name")
    @NonNull
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equal(getId(), coach.getId()) && Objects.equal(getTeamId(), coach.getTeamId())
                && Objects.equal(getRating(), coach.getRating()) && Objects.equal(getName(), coach.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getTeamId(), getRating(), getName());
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", clubId=" + teamId +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                '}';
    }
}
