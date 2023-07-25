package com.footsim.domain.dto;

import com.footsim.domain.model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the top scorers and top assistants.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TopActionsDTO {

    /**
     * Id of a {@link Player}
     */
    private Long id;

    /**
     * Amount of goals or assists of a {@link Player}
     */
    private Short actions;

}
