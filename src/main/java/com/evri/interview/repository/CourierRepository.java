package com.evri.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing CourierEntity objects in the database.
 */
@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {

    /**
     * Retrieves a list of active couriers.
     *
     * @return List of CourierEntity objects representing active couriers.
     */
    List<CourierEntity> findByActiveTrue();

    /**
     * Retrieves a courier by its unique identifier.
     *
     * @param id The unique identifier of the courier.
     * @return An Optional containing the CourierEntity object if found, otherwise empty.
     */
    Optional<CourierEntity> findById(Long id);

}
