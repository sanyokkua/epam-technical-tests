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
import java.util.stream.Collectors;

/**
 * Global error handler for handling exceptions thrown by controllers.
 */
@ControllerAdvice
public class GlobalErrorHandler {

    /**
     * Handles generic exceptions and returns an appropriate HTTP response.
     *
     * @param ex      The exception being handled.
     * @param request The current web request.
     * @return A ResponseEntity containing the error response.
     */
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

    /**
     * Handles MethodArgumentNotValidException exceptions thrown during validation and returns an appropriate HTTP response.
     *
     * @param ex      The MethodArgumentNotValidException being handled.
     * @param request The current web request.
     * @return A ResponseEntity containing the error response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        var message = errors.entrySet().stream()
                               .map(entry -> String.format("Field '%s', Error: %s",
                                                           entry.getKey(),
                                                           entry.getValue()
                                                          ))
                               .collect(Collectors.joining(";"));

        var response = ErrorResponse.builder()
                                              .timestamp(LocalDateTime.now().toString())
                                              .status(status.value())
                                              .error(ex.getClass().getSimpleName())
                                              .message(message)
                                              .path(request.getDescription(false).substring(4))
                                              .build();

        return new ResponseEntity<>(response, status);
    }

}
