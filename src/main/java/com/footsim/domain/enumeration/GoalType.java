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
    AUTOGOAL;

    public static GoalType getType(double possibility){
        if(possibility<0.80&&possibility>=0){
            return DEFAULT;
        } else if (possibility>=0.80&&possibility<0.95) {
            return PENALTY;
        } else if (possibility<1.00&&possibility>=0.95) {
            return  AUTOGOAL;
        } else return null;
    }

}
