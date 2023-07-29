package com.footsim.service.exceptions;

import com.footsim.domain.dto.ResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ResponseDTO> handleEntityNotFoundException(EntityNotFoundException notFoundException) {

        ResponseDTO response = new ResponseDTO();
        if (notFoundException.getMessage() != null) response.setMessage(notFoundException.getMessage());
        else response.setMessage("Entity Not Found");
        response.setStatusCode(404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RosterUnavailableException.class)
    protected ResponseEntity<ResponseDTO> handleRosterUnavailableException(RosterUnavailableException unavailableException) {

        ResponseDTO response = new ResponseDTO();
        if (unavailableException.getMessage() != null) response.setMessage(unavailableException.getMessage());
        else response.setMessage("Starting roster is not formed for the current Team");
        response.setStatusCode(403);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}