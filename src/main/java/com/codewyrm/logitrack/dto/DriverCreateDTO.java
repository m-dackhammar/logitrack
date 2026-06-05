package com.codewyrm.logitrack.dto;

import com.codewyrm.logitrack.domain.Driver;

public record DriverCreateDTO(
     String employeeId,
     String firstName,
     String lastName,
     String licenseNumber
) {
    public Driver toEntity() {
        return new Driver(employeeId, firstName, lastName, licenseNumber);
    }
}
