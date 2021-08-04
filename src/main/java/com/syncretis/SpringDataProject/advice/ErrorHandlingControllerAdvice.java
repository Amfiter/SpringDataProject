package com.syncretis.SpringDataProject.advice;

import com.syncretis.SpringDataProject.exceptions.DepartmentException;
import com.syncretis.SpringDataProject.exceptions.DocumentException;
import com.syncretis.SpringDataProject.dto.ValidationErrorResponse;
import com.syncretis.SpringDataProject.dto.Violation;
import com.syncretis.SpringDataProject.exceptions.LanguageException;
import com.syncretis.SpringDataProject.exceptions.PersonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(DocumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String onDocumentException (DocumentException e) { return e.getMessage(); }

    @ExceptionHandler(LanguageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String onLanguageException (LanguageException e) { return e.getMessage(); }

    @ExceptionHandler(DepartmentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String onDepartmentException (DepartmentException e) { return e.getMessage(); }

    @ExceptionHandler(PersonException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String onPersonException (PersonException e) { return e.getMessage(); }
}