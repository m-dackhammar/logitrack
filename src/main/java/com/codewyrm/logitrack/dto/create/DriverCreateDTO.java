package com.codewyrm.logitrack.dto.create;

import com.codewyrm.logitrack.domain.Driver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DriverCreateDTO(
    @NotBlank( message = "Cannot be blank") @Size(min = 2, max = 50, message = "")
    String name,

    String employeeId,
    String firstName,
    String lastName,
    String licenseNumber
) {
    public Driver toEntity() {
        return new Driver(employeeId, firstName, lastName, licenseNumber);
    }
}
