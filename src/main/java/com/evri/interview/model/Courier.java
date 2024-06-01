package com.evri.interview.model;

import lombok.Builder;
import lombok.Data;

/**
 * A class representing a Courier.
 */
@Data
@Builder
public class Courier {

    /**
     * The unique identifier for the courier.
     */
    private long id;
    /**
     * The full name of the courier.
     */
    private String name;
    /**
     * Indicates whether the courier is active or not.
     */
    private boolean active;

}
