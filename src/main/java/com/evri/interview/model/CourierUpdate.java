package com.evri.interview.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A class representing an update model for a Courier entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierUpdate {

    /**
     * The first name of the courier.
     */
    @NotNull(message = "firstName can't be NULL")
    @NotBlank(message = "firstName can't be blank")
    private String firstName;
    /**
     * The last name of the courier.
     */
    @NotNull(message = "lastName can't be NULL")
    @NotBlank(message = "lastName can't be blank")
    private String lastName;
    /**
     * Indicates whether the courier is active or not.
     */
    private boolean active;

}
