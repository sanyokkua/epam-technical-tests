package com.evri.interview.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Entity class representing a Courier stored in the database.
 */
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "couriers")
public class CourierEntity {

    /**
     * The unique identifier for the courier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    /**
     * The first name of the courier.
     */
    @Column(name = "FST_NME")
    private String firstName;
    /**
     * The last name of the courier.
     */
    @Column(name = "LST_NME")
    private String lastName;
    /**
     * Indicates whether the courier is active or not.
     */
    @Column(name = "ACTV")
    private boolean active;

}
