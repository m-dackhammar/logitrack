package com.codewyrm.logitrack.dto;

import com.codewyrm.logitrack.domain.Driver;
import com.codewyrm.logitrack.domain.MaintenanceTask;
import com.codewyrm.logitrack.domain.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaintenanceTaskCreateDTO(
    String licensePlate,
    String description,
    BigDecimal cost,
    LocalDate scheduledDate,
    LocalDate completionDate
) {
    public MaintenanceTask toEntity(Vehicle vehicle) {
        return new MaintenanceTask(
            vehicle,
            description,
            cost,
            scheduledDate
        );
    }
}
