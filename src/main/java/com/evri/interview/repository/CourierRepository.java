package com.evri.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
