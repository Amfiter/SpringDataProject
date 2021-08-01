package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LanguageException extends RuntimeException {
    public LanguageException(HttpStatus status) {
        throw new ResponseStatusException(
                status, "Language " + status.getReasonPhrase());
    }
}
