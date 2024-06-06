package com.rentme.app.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorMessage {

    private String code;
    private String message;
    private HttpStatus status;
    private String uri;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

}
