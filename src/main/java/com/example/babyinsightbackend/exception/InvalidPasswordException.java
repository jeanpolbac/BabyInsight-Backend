package com.example.babyinsightbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidPasswordException extends RuntimeException{

    /**
     * Constructs an instance with a custom error message.
     *
     * @param message The custom error message.
     */
    public InvalidPasswordException(String message) {
        super(message);
    }

}
