package com.evri.interview.exceptions;

/**
 * Custom exception class for representing cases where a courier is not found.
 */
public class CourierIsNotFound extends RuntimeException {

    /**
     * Constructs a new CourierIsNotFound exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public CourierIsNotFound(String message) {
        super(message);
    }

}
