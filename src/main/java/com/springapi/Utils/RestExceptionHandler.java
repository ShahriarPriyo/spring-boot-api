package com.springapi.Utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

public interface RestExceptionHandler {
    public ResponseEntity<Object> handleException();

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Object> handleException(EntityNotFoundException ex);

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleException(ConstraintViolationException ex);
}
