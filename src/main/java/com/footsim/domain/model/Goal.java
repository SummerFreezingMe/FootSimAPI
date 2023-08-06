package com.footsim.domain.model;

import com.footsim.domain.enumeration.GoalType;
import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;

/**
 * A Goal.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id")
    @NonNull
    private Long matchId;

    @Column(name = "author_id")
    @NonNull
    private Long authorId;

    @Column(name = "assist_id")
    private Long assistId;

    @Column(name = "minute")
    @NonNull
    private Short minute;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NonNull
    private GoalType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Objects.equal(getId(), goal.getId()) && Objects.equal(getMatchId(), goal.getMatchId()) && Objects.equal(getAuthorId(), goal.getAuthorId()) && Objects.equal(getAssistId(), goal.getAssistId()) && Objects.equal(getMinute(), goal.getMinute()) && getType() == goal.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getMatchId(), getAuthorId(), getAssistId(), getMinute());
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", authorId=" + authorId +
                ", assistId=" + assistId +
                ", minute=" + minute +
                ", type=" + type +
                '}';
    }
}
