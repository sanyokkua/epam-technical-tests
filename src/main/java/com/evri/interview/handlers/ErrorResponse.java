package com.evri.interview.handlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an error response sent by the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    /**
     * The timestamp when the error occurred.
     */
    private String timestamp;
    /**
     * The HTTP status code of the response.
     */
    private int status;
    /**
     * The type of error.
     */
    private String error;
    /**
     * A message describing the error.
     */
    private String message;
    /**
     * The path of the request that caused the error.
     */
    private String path;

}
