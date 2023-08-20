package com.footsim.domain.dto;

import com.footsim.domain.model.Player;

/**
 * A DTO for the top scorers and top assistants.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public interface TopActionsDTO
{

    /**
     * Id of a {@link Player}
     */
    Long getId();

    /**
     * Amount of goals or assists of a {@link Player}
     */
    Short getActions();

}
