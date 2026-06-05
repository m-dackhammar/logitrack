package com.codewyrm.logitrack.dto;

import com.codewyrm.logitrack.domain.Driver;
import com.codewyrm.logitrack.domain.Vehicle;

public record VehicleCreateDTO(
     String vin,
     String licensePlate,
     String brand,
     String model
) {
    public Vehicle toEntity() {
        return new Vehicle(vin, licensePlate, brand, model);
    }
}
