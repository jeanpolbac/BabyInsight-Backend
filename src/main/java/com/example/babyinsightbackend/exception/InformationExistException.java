package com.example.babyinsightbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when the information is not found in the system.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {
    public InformationExistException(String message) { super(message); }
}

