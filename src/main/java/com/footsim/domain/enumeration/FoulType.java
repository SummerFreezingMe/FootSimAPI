package com.footsim.domain.enumeration;

import com.footsim.domain.model.Foul;

/**
 * Type of suffered {@link Foul} enumeration
 */
public enum FoulType {
    /**
     * Light foul without serious penalty
     */
    NO_CARD,
    /**
     * Average foul, culprit gets a yellow card as a penalty, second yellow card means send-off
     */
    YELLOW_CARD,

    /**
     * Severe foul, culprit gets a red card and a send-off as a penalty
     */
    RED_CARD;

    /**
     * Generates foul type depending on a numeric event possibility
     * @param possibility floating point number from 0 to 1
     * @return {@link FoulType}
     */
    public static FoulType getType(double possibility){
        if(possibility<0.85&&possibility>=0){
            return NO_CARD;
        } else if (possibility>=0.85&&possibility<0.99) {
            return YELLOW_CARD;
        } else if (possibility<1.00&&possibility>=0.99) {
            return  RED_CARD;
        } else return null;
    }
}
