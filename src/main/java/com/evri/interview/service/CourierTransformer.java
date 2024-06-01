package com.evri.interview.service;

import com.evri.interview.model.Courier;
import com.evri.interview.repository.CourierEntity;
import org.springframework.stereotype.Component;

/**
 * Component responsible for transforming CourierEntity objects to Courier objects.
 */
@Component
public class CourierTransformer {

    /**
     * Transforms a CourierEntity object into a Courier object.
     *
     * @param entity The CourierEntity object to transform.
     * @return The corresponding Courier object.
     */
    public Courier toCourier(CourierEntity entity) {
        return Courier.builder()
                      .id(entity.getId())
                      .name(String.format("%s %s", entity.getFirstName(), entity.getLastName()))
                      .active(entity.isActive())
                      .build();
    }

}
