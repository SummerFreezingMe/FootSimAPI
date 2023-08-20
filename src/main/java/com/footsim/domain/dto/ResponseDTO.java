package com.footsim.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * A DTO for the Response.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class ResponseDTO {

    @NotNull(message = "id cannot be null")
    private int statusCode;

    @NotNull(message = "message cannot be null")
    private String message;

}