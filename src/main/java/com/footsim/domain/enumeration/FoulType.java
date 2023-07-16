package com.footsim.domain.enumeration;

public enum FoulType {
    NO_CARD,
    YELLOW_CARD,
    RED_CARD;

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
