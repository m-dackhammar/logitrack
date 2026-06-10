package com.codewyrm.logitrack.dto.create;

import com.codewyrm.logitrack.domain.Driver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DriverCreateDTO(
    @NotBlank( message = "Employee ID cannot be blank")
    @Pattern(regexp = "^[A-Z0-9\\-]+$", message = "Employee ID must contain only uppercase letters, numbers, and hyphens")
    @Size(min = 5, max = 20, message = "Employee ID must be between 3 and 20 characters")
    String employeeId,

    @NotBlank( message = "Firstname cannot be blank") @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
    String firstName,

    @NotBlank( message = "Lastname cannot be blank") @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
    String lastName,

    @NotBlank( message = "License number cannot be blank") @Size(min = 10, max = 30, message = "License number must be between 10 and 30 characters")
    String licenseNumber
) {
    public Driver toEntity() {
        return new Driver(employeeId, firstName, lastName, licenseNumber);
    }
}
