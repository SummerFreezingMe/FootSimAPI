package com.footsim.service.exceptions;

import java.io.Serial;

public class RosterUnavailableException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RosterUnavailableException() {
        super("Starting roster is not formed for one of the Teams");
    }
}
