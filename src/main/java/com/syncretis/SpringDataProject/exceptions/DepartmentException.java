package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DepartmentException extends RuntimeException {
    public DepartmentException(HttpStatus status) {
        throw new ResponseStatusException(
                status, "Department " + status.getReasonPhrase());
    }
}
