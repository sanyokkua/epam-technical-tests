package com.evri.interview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CourierRepositoryTest {

    @Autowired
    private CourierRepository courierRepository;

    @Test
    void testFindAllCouriers() {
        List<CourierEntity> all = courierRepository.findAll();

        assertNotNull(all);
        assertFalse(all.isEmpty());
        assertEquals(5, all.size());

        courierRepository.deleteAll();

        List<CourierEntity> newValues = courierRepository.findAll();

        assertNotNull(newValues);
        assertTrue(newValues.isEmpty());
    }

    @Test
    void testFindByActiveTrue() {
        List<CourierEntity> all = courierRepository.findByActiveTrue();

        assertNotNull(all);
        assertFalse(all.isEmpty());
        assertEquals(3, all.size());

        courierRepository.deleteById(1L);

        List<CourierEntity> newValues = courierRepository.findByActiveTrue();

        assertNotNull(newValues);
        assertFalse(newValues.isEmpty());
        assertEquals(2, newValues.size());
    }

    @Test
    void testFindById_CourierExistAndDoesNotExist() {
        Optional<CourierEntity> foundByID1 = courierRepository.findById(1L);

        assertNotNull(foundByID1);
        assertTrue(foundByID1.isPresent());
        assertEquals(1L, foundByID1.get().getId());
        assertEquals("Ben", foundByID1.get().getFirstName());
        assertEquals("Askew", foundByID1.get().getLastName());
        assertTrue(foundByID1.get().isActive());

        Optional<CourierEntity> foundByID666 = courierRepository.findById(666L);

        assertNotNull(foundByID666);
        assertFalse(foundByID666.isPresent());
    }

}