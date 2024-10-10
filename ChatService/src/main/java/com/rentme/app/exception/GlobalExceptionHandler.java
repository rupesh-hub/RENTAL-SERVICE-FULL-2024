package com.rentme.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle specific exceptions
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String modifiedMessage = "Invalid input provided: " + ex.getMessage();  // Modify the message here
        return new ResponseEntity<>(modifiedMessage, HttpStatus.BAD_REQUEST);
    }

    // Handle generic runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        String modifiedMessage = "An error occurred: " + ex.getMessage();  // Modify the message here
        return new ResponseEntity<>(modifiedMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle all other exceptions (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        String modifiedMessage = "Unexpected error: " + ex.getMessage();  // Modify the message here
        return new ResponseEntity<>(modifiedMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
