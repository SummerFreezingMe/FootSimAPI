package com.footsim.domain.model;

import com.footsim.domain.enumeration.FoulType;
import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foul")
public class Foul {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "minute")
    private Short minute;

    @Column(name = "type")
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
