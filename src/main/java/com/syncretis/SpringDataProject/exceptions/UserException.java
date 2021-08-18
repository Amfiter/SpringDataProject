package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
    public UserException(HttpStatus status) {
        super("User " + status.getReasonPhrase());
    }
}
