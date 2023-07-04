package com.footsim.domain.dto;


import com.footsim.domain.enumeration.GoalType;
import com.footsim.domain.model.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link Goal} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoalDTO implements Serializable {
    private Long id;

    private Long matchId;

    private Long authorId;

    private Long assistId;

    private Short minute;

    private GoalType type;

}
