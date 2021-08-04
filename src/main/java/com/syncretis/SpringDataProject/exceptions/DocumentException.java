package com.syncretis.SpringDataProject.exceptions;

import org.springframework.http.HttpStatus;

public class DocumentException extends RuntimeException {
    public DocumentException(HttpStatus status) {
        super("Document " + status.getReasonPhrase());
    }
}