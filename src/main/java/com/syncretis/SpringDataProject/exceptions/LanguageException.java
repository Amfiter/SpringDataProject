package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;

public class LanguageException extends RuntimeException {
    public LanguageException(HttpStatus status) {
        super("Language " + status.getReasonPhrase());
    }
}
