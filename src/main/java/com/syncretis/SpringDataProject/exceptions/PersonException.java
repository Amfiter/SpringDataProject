package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;

public class PersonException extends RuntimeException {
    public PersonException(HttpStatus status) {
        super("Person " + status.getReasonPhrase());
    }
}
