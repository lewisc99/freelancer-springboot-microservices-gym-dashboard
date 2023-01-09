package com.lewis.msemployee.config.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static  final long SerialVersionUID = 1L;

    public ResourceNotFoundException(Object id)
    {
        super("Resource not found: ID " + id);
    }
}
