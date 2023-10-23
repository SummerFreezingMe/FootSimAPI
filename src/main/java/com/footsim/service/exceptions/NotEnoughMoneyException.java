package com.footsim.service.exceptions;

import com.footsim.domain.model.Club;

import java.io.Serial;

public class NotEnoughMoneyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotEnoughMoneyException(Club club                                            ) {
        super("Team %s dont have enough money for transfer".formatted(club.getName()));
    }
}

