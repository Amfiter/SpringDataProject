package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PersonException extends RuntimeException {
    public PersonException(HttpStatus status) {
        throw new ResponseStatusException(
                status, "Person " + status.getReasonPhrase());
    }
}
