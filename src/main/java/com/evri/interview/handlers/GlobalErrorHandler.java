package com.evri.interview.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse response = ErrorResponse.builder()
                                              .timestamp(LocalDateTime.now().toString())
                                              .status(status.value())
                                              .error(ex.getClass().getSimpleName())
                                              .message(ex.getMessage())
                                              .path(request.getDescription(false).substring(4))
                                              .build();

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String message = errors.entrySet().stream()
                               .map(entry -> String.format("Field '%s', Error: %s",
                                                           entry.getKey(),
                                                           entry.getValue()
                                                          ))
                               .collect(Collectors.joining(";"));

        ErrorResponse response = ErrorResponse.builder()
                                              .timestamp(LocalDateTime.now().toString())
                                              .status(status.value())
                                              .error(ex.getClass().getSimpleName())
                                              .message(message)
                                              .path(request.getDescription(false).substring(4))
                                              .build();

        return new ResponseEntity<>(response, status);
    }

}