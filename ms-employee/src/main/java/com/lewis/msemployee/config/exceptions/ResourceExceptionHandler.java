package com.lewis.msemployee.config.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(
            ResourceNotFoundException e, HttpServletRequest request
    )
    {
        String errorMessage = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError errorModel = new StandardError(Instant.now(), status.value(),
                errorMessage, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> Database(DatabaseException exception, HttpServletRequest request)
    {
        String errorMessage = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError errorModel = new StandardError(Instant.now(), status.value(),
                errorMessage, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(errorModel);
    }

}
