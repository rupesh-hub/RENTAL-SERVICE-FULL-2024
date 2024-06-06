package com.rentme.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus status;
    private final String errorCode;

    public ApiException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = "INTERNAL_SERVER_ERROR";
    }

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.errorCode = status.name();
    }

    public ApiException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = "INTERNAL_SERVER_ERROR";
    }

    public ApiException(String message, HttpStatus status, String errorCode, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.errorCode = errorCode;
    }

}
