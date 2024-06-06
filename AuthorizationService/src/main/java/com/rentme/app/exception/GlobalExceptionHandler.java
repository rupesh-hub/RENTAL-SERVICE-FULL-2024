package com.rentme.app.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(LockedException.class)
//    public ResponseEntity<ErrorMessage> handleException(LockedException exp) {
//        return ResponseEntity
//                .status(UNAUTHORIZED)
//                .body(
//                        ErrorMessage.builder()
//                                .code(ACCOUNT_LOCKED.getCode())
//                                .message(ACCOUNT_LOCKED.getDescription())
//                                .build()
//                );
//    }

//    @ExceptionHandler(DisabledException.class)
//    public ResponseEntity<ExceptionResponse> handleException(DisabledException exp) {
//        return ResponseEntity
//                .status(UNAUTHORIZED)
//                .body(
//                        ExceptionResponse.builder()
//                                .businessErrorCode(ACCOUNT_DISABLED.getCode())
//                                .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
//                                .error(exp.getMessage())
//                                .build()
//                );
//    }
//
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ExceptionResponse> handleException() {
//        return ResponseEntity
//                .status(UNAUTHORIZED)
//                .body(
//                        ExceptionResponse.builder()
//                                .businessErrorCode(BAD_CREDENTIALS.getCode())
//                                .businessErrorDescription(BAD_CREDENTIALS.getDescription())
//                                .error("Login and / or Password is incorrect")
//                                .build()
//                );
//    }
//
//    @ExceptionHandler(MessagingException.class)
//    public ResponseEntity<ExceptionResponse> handleException(MessagingException exp) {
//        return ResponseEntity
//                .status(INTERNAL_SERVER_ERROR)
//                .body(
//                        ExceptionResponse.builder()
//                                .error(exp.getMessage())
//                                .build()
//                );
//    }
//
//    @ExceptionHandler(ActivationTokenException.class)
//    public ResponseEntity<ExceptionResponse> handleException(ActivationTokenException exp) {
//        return ResponseEntity
//                .status(BAD_REQUEST)
//                .body(
//                        ExceptionResponse.builder()
//                                .error(exp.getMessage())
//                                .build()
//                );
//    }
//
//    @ExceptionHandler(OperationNotPermittedException.class)
//    public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exp) {
//        return ResponseEntity
//                .status(BAD_REQUEST)
//                .body(
//                        ExceptionResponse.builder()
//                                .error(exp.getMessage())
//                                .build()
//                );
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {

        Map<String, String> errors = new HashMap<>();

        exp.getBindingResult()
                .getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorMessage.builder()
                                .timestamp(LocalDateTime.now())
                                .code("400")
                                .status(BAD_REQUEST)
                                .errors(errors)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exp, HttpServletRequest request) {
        exp.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorMessage.builder()
                                .message(exp.getLocalizedMessage())
                                .code("500")
                                .timestamp(LocalDateTime.now())
                                .status(INTERNAL_SERVER_ERROR)
                                .uri(request.getRequestURI())
                                .build()
                );
    }

}
