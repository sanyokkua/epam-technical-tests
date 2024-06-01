package com.evri.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {

    List<CourierEntity> findByActiveTrue();

    Optional<CourierEntity> findById(Long id);

}
