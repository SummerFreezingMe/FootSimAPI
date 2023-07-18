package com.footsim.domain.enumeration;

import com.footsim.domain.model.Match;
import com.footsim.domain.model.Player;

/**
 * Status of a {@link Player} enumeration.
 */
public enum PlayerStatus {
    /**
     * {@link Player} are playing in the current, or next, {@link Match}.
     */
    ROSTER,

    /**
     * {@link Player} are available for substitution in the current, or next, {@link Match}.
     */
    BENCH,
    /**
     * {@link Player} are not available for the current, or next, {@link Match}.
     */
    OUT,
    /**
     * {@link Player} are injured and not for the current, or next, {@link Match}.
     */
    INJURED,

    /**
     * {@link Player} are disqualified and not available for the current, or next, {@link Match}.
     */
    DISQUALIFIED,

    /**
     * {@link Player} got sent off and not available for the current, or next, {@link Match}.
     */
    SENT_OFF
}
