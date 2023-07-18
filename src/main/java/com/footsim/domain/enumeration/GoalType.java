package com.footsim.domain.enumeration;

import com.footsim.domain.model.Goal;

/**
 * Type of {@link Goal} enumeration.
 */

public enum GoalType {
    /**
     * Regular {@link Goal} scored in a game
     */
    DEFAULT,


    /**
     * {@link Goal} scored from a penalty strike
     */
    PENALTY,


    /**
     * {@link Goal} scored by a player against their own team
     */
    AUTOGOAL
}
