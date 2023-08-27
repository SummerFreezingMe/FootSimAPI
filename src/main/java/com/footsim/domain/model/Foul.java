package com.footsim.domain.model;

import com.footsim.domain.enumeration.FoulType;
import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Foul.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "foul")
public class Foul {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id")
    @NonNull
    private Long matchId;

    @Column(name = "player_id")
    @NonNull
    private Long playerId;

    @Column(name = "foul_minute")
    @NonNull
    private Short minute;

    @Enumerated(EnumType.STRING)
    @Column(name = "foul_type")
    @NonNull
    private FoulType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foul foul = (Foul) o;
        return Objects.equal(getId(), foul.getId()) && Objects.equal(getMatchId(), foul.getMatchId()) && Objects.equal(getPlayerId(), foul.getPlayerId()) && Objects.equal(getMinute(), foul.getMinute()) && getType() == foul.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getMatchId(), getPlayerId(), getMinute(), getType());
    }

    @Override
    public String toString() {
        return "Foul{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", playerId=" + playerId +
                ", minute=" + minute +
                ", type=" + type +
                '}';
    }
}
