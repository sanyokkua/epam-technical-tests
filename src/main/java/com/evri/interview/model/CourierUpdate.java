package com.evri.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierUpdate {

    private String firstName;
    private String lastName;
    private boolean active;

}
