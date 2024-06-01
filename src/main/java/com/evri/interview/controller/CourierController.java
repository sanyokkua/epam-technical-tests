package com.evri.interview.controller;

import com.evri.interview.model.Courier;
import com.evri.interview.model.CourierUpdate;
import com.evri.interview.service.CourierService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for managing Courier endpoints.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CourierController {

    private CourierService courierService;

    /**
     * Retrieves a list of all couriers optionally filtered by active status.
     *
     * @param isActive Flag indicating whether to filter by active status.
     * @return ResponseEntity containing a list of Courier objects.
     */
    @GetMapping("/couriers")
    public ResponseEntity<List<Courier>> getAllCouriers(
            @RequestParam(name = "isActive", required = false, defaultValue = "false") boolean isActive) {
        return ResponseEntity.ok(courierService.getAllCouriers(isActive));
    }

    /**
     * Updates the details of a courier with the provided ID.
     *
     * @param courierId      The unique identifier of the courier to update.
     * @param updatedCourier The updated details for the courier.
     * @return ResponseEntity containing the updated Courier object.
     */
    @PutMapping("/couriers/{courierId}")
    public ResponseEntity<Courier> updateCourier(
            @PathVariable Long courierId,
            @RequestBody @Valid CourierUpdate updatedCourier) {

        Courier updated = courierService.updateCourier(courierId, updatedCourier);
        return ResponseEntity.ok(updated);
    }

}
