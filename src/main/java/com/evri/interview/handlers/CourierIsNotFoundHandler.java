package com.evri.interview.handlers;

import com.evri.interview.exceptions.CourierIsNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Global exception handler for CourierIsNotFound exceptions.
 */
@ControllerAdvice
public class CourierIsNotFoundHandler {

    /**
     * Handles CourierIsNotFound exceptions and returns an appropriate HTTP response.
     *
     * @param ex      The CourierIsNotFound exception.
     * @param request The current web request.
     * @return A ResponseEntity containing the error response.
     */
    @ExceptionHandler(CourierIsNotFound.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(CourierIsNotFound ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse response = ErrorResponse.builder()
                                              .timestamp(LocalDateTime.now().toString())
                                              .status(status.value())
                                              .error(ex.getClass().getSimpleName())
                                              .message(ex.getMessage())
                                              .path(request.getDescription(false).substring(4))
                                              .build();

        return new ResponseEntity<>(response, status);
    }

}
