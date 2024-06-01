package com.evri.interview.service;

import com.evri.interview.exceptions.CourierIsNotFound;
import com.evri.interview.model.Courier;
import com.evri.interview.model.CourierUpdate;
import com.evri.interview.repository.CourierEntity;
import com.evri.interview.repository.CourierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourierService {

    private CourierTransformer courierTransformer;
    private CourierRepository repository;

    public List<Courier> getAllCouriers(boolean isActive) {
        if (isActive) {
            return repository.findByActiveTrue().stream()
                             .map(courierTransformer::toCourier)
                             .collect(Collectors.toList());
        }

        return repository.findAll()
                         .stream()
                         .map(courierTransformer::toCourier)
                         .collect(Collectors.toList());
    }

    public Courier updateCourier(Long courierId, CourierUpdate updatedCourier) {
        Optional<CourierEntity> entity = repository.findById(courierId);
        if (!entity.isPresent()) {
            throw new CourierIsNotFound("Courier not found for provided id " + courierId);
        }

        CourierEntity existingCourier = entity.get();
        existingCourier.setFirstName(updatedCourier.getFirstName());
        existingCourier.setLastName(updatedCourier.getLastName());
        existingCourier.setActive(updatedCourier.isActive());

        CourierEntity updated = repository.save(existingCourier);

        return courierTransformer.toCourier(updated);
    }

}
