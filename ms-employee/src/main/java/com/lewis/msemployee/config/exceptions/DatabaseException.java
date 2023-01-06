package com.lewis.msemployee.config.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String message)
    {
        super(message);
    }
}
