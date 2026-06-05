package com.codewyrm.logitrack.dto;

import com.codewyrm.logitrack.domain.Driver;

import java.util.UUID;

public record DriverResponseDTO (
    UUID id,
    String employeeId,
    String firstName,
    String lastName,
    String licenseNumber
) {
    public static DriverResponseDTO fromEntity(Driver driver) {
        return new DriverResponseDTO(
            driver.getId(),
            driver.getEmployeeId(),
            driver.getFirstName(),
            driver.getLastName(),
            driver.getLicenseNumber());
    }
}
