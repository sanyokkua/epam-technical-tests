package com.evri.interview.service;

import com.evri.interview.model.Courier;
import com.evri.interview.repository.CourierEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CourierTransformerTest {

    @Test
    void testToCourier_ConversionSuccessful() {
        CourierTransformer transformer = new CourierTransformer();

        CourierEntity entity = CourierEntity.builder()
                                            .id(11L)
                                            .firstName("Name")
                                            .lastName("Last")
                                            .active(true)
                                            .build();

        Courier courier = transformer.toCourier(entity);

        assertNotNull(courier);
        assertEquals(entity.getId(), courier.getId());
        assertEquals(entity.isActive(), courier.isActive());
        assertEquals("Name Last", courier.getName());
    }

}