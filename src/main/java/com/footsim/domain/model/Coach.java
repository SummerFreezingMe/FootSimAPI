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
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "rating")
    @NonNull
    private Integer rating;

    @Column(name = "name")
    @NonNull
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equal(getId(), coach.getId()) && Objects.equal(getClubId(), coach.getClubId())
                && Objects.equal(getRating(), coach.getRating()) && Objects.equal(getName(), coach.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getClubId(), getRating(), getName());
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", clubId=" + clubId +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                '}';
    }
}
