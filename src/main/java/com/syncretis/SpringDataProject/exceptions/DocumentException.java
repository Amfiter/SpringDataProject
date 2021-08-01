package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DocumentException extends RuntimeException {
    public DocumentException(HttpStatus status) {
        throw new ResponseStatusException(
                status, "Document " + status.getReasonPhrase());
    }
}
