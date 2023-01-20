package com.lewis.msuser.config.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

        @ExceptionHandler(value = IllegalArgumentException.class)
        protected ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException exception, HttpServletRequest request)
        {
            String messageError = "Invalid Input please verify!";
            HttpStatus status = HttpStatus.BAD_REQUEST;

            StandardError standardError =  new StandardError(
                Instant.now(),status.value(), exception.getMessage(), messageError,request.getRequestURI()
            );

            return ResponseEntity.badRequest().body(standardError);
        }

        @ExceptionHandler(value= NullPointerException.class)
        protected  ResponseEntity<StandardError> NullPointException(NullPointerException exception, HttpServletRequest request)
        {
            String messageError = "Invalid  User ID Not Found: " + exception.getMessage();
            String error = "not found ID";
            HttpStatus status = HttpStatus.NOT_FOUND;
            StandardError standardError = new StandardError(
                    Instant.now(), status.value(), error, messageError, request.getRequestURI()
            );

            return ResponseEntity.status(status).body(standardError);
        }

        @ExceptionHandler(value= EmptyResultDataAccessException.class)
        protected  ResponseEntity<StandardError> EmptyResultDataAccessException(EmptyResultDataAccessException exception, HttpServletRequest request)
        {
            String messageError = "Invalid  User ID Not Found.";
            HttpStatus status = HttpStatus.NOT_FOUND;
            StandardError standardError = new StandardError(
                    Instant.now(), status.value(), exception.getMessage(), messageError, request.getRequestURI()
            );

            return ResponseEntity.status(status).body(standardError);
        }

        @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
        protected  ResponseEntity<StandardError> HttpRequestMethodNotSupportedException
                (HttpRequestMethodNotSupportedException exception, HttpServletRequest request)
        {
            String messageError = "Request Method Not Supported";
            HttpStatus status = HttpStatus.BAD_REQUEST;
            StandardError standardError = new StandardError(
                    Instant.now(), status.value(), exception.getMessage(), messageError, request.getRequestURI()
            );
            return ResponseEntity.status(status).body(standardError);
        }

        @ExceptionHandler(value = ConstraintViolationException.class)
        protected ResponseEntity<StandardError>  ConstraintViolationException
                (ConstraintViolationException exception, HttpServletRequest request)
        {
            String messageError = "Please certify if the data is correct " +
                    "Another User With same data have been found";

            HttpStatus status = HttpStatus.BAD_REQUEST;
            StandardError standardError = new StandardError(
                    Instant.now(), status.value(), exception.getMessage(), messageError, request.getRequestURI()
            );

            return ResponseEntity.status(status).body(standardError);
        }

}