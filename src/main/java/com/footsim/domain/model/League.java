package com.footsim.domain.model;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;

/**
 * A League.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "league")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class League implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "participants")
    private Integer participants;

    @Column(name = "name")
    @NonNull
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return Objects.equal(getId(), league.getId()) && Objects.equal(getParticipants(), league.getParticipants()) && Objects.equal(getName(), league.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName());
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", participants=" + participants +
                ", name='" + name + '\'' +
                '}';
    }


}
