package com.evri.interview.handlers;

import com.evri.interview.exceptions.CourierIsNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourierIsNotFoundHandler {

    @ExceptionHandler(CourierIsNotFound.class)
    public ResponseEntity<String> handleEntityNotFoundException(CourierIsNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
