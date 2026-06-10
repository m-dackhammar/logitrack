package com.codewyrm.logitrack.dto.create;

import com.codewyrm.logitrack.domain.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VehicleCreateDTO(
    @NotBlank(message = "VIN cannot be blank")
    @Size(min = 17, max = 17, message = "VIN must be exactly 17 characters long")
    String vin,

    @NotBlank(message = "License plate cannot be blank")
    @Size(max = 10, message = "License plate cannot exceed 10 characters")
    String licensePlate,

    @NotBlank(message = "Brand cannot be blank")
    @Size(max = 50, message = "Brand cannot exceed 50 characters")
    String brand,

    @NotBlank(message = "Model cannot be blank")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    String model
) {
    public Vehicle toEntity() {
        return new Vehicle(vin, licensePlate, brand, model);
    }
}
