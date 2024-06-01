package com.evri.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierUpdate {

    @NotNull(message = "firstName can't be NULL")
    @NotBlank(message = "firstName can't be blank")
    private String firstName;
    @NotNull(message = "lastName can't be NULL")
    @NotBlank(message = "lastName can't be blank")
    private String lastName;
    private boolean active;

}
