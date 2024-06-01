package com.evri.interview.exceptions;

public class CourierIsNotFound extends RuntimeException {

    public CourierIsNotFound() {
        this("Courier Not Found");
    }

    public CourierIsNotFound(String message) {
        super(message);
    }

}
