package com.lewis.msemployee.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> NullPointException(NullPointerException exception, HttpServletRequest request)
    {
        String errorMessage = "Employee Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError errorModel = new StandardError(Instant.now(), status.value(),
                errorMessage, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> ValidationBodyException(MethodArgumentNotValidException exception, HttpServletRequest request)
    {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String messageError = "Validation failed for 'employee'. Error count: ";
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });

        messageError += errors.size();

        ValidationError errorModel = new ValidationError(Instant.now(), status.value(), messageError,
                errors, request.getRequestURI());

        return ResponseEntity.status(status).body(errorModel);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> GlobalRuntimeException(RuntimeException exception, HttpServletRequest request)
    {
        String errorMessage = "Something went Wrong please wait and send a request again later";

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError errorModel = new StandardError(Instant.now(), status.value(),
                errorMessage, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(errorModel);

    }




}
