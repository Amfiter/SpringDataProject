package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;

public class RoleException extends RuntimeException {
    public RoleException(HttpStatus status) {
        super("Role " + status.getReasonPhrase());
    }
}