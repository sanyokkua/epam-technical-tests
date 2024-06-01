package com.evri.interview.controller;

import com.evri.interview.model.Courier;
import com.evri.interview.model.CourierUpdate;
import com.evri.interview.service.CourierService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CourierController {

    private CourierService courierService;

    @GetMapping("/couriers")
    public ResponseEntity<List<Courier>> getAllCouriers(
            @RequestParam(name = "isActive", required = false, defaultValue = "false") boolean isActive) {
        return ResponseEntity.ok(courierService.getAllCouriers(isActive));
    }

    @PutMapping("/couriers/{courierId}")
    public ResponseEntity<Courier> updateCourier(
            @PathVariable Long courierId,
            @RequestBody CourierUpdate updatedCourier) {

        Courier updated = courierService.updateCourier(courierId, updatedCourier);
        return ResponseEntity.ok(updated);
    }

}
