package com.footsim.domain.dto;

import lombok.Data;

/**
 * A DTO for the Response.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class ResponseDTO {

    private int statusCode;

    private String message;

}