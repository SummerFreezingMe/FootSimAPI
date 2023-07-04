package com.footsim.domain.model;

import com.footsim.domain.enumeration.GoalType;
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
@Table(name = "goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "assist_id")
    private Long assistId;

    @Column(name = "minute")
    private Short minute;

    @Column(name = "type")
    private GoalType type;
}
