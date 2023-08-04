package com.footsim.domain.dto;

import com.footsim.domain.model.Player;
import lombok.*;

/**
 * A DTO for the top scorers and top assistants.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
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
