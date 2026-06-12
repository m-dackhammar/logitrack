package com.codewyrm.logitrack.dto.response;

import com.codewyrm.logitrack.domain.Vehicle;

import java.util.UUID;

public record VehicleResponseDTO(
    UUID id,
    String vin,
    String licensePlate,
    String brand,
    String model,
    String driverEmployeeId
) {
    public static VehicleResponseDTO fromEntity(Vehicle vehicle) {
        return new VehicleResponseDTO(
            vehicle.getId(),
            vehicle.getVin(),
            vehicle.getLicensePlate(),
            vehicle.getBrand(),
            vehicle.getModel(),
            vehicle.getDriver().getEmployeeId());
    }
}
