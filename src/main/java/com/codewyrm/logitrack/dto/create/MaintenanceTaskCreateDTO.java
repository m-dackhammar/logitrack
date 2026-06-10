package com.codewyrm.logitrack.dto.create;

import com.codewyrm.logitrack.domain.MaintenanceTask;
import com.codewyrm.logitrack.domain.Vehicle;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaintenanceTaskCreateDTO(
    @NotBlank(message = "License plate cannot be blank")
    @Size(max = 10, message = "License plate cannot exceed 10 characters")
    String licensePlate,

    @NotBlank(message = "Description cannot be blank")
    String description,

    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Cost cannot be negative")
    @Digits(integer = 8, fraction = 2, message = "Cost must match decimal format (up to 8 digits before decimal, 2 after)")
    BigDecimal cost,

    @NotNull(message = "Scheduled date is required")
    @FutureOrPresent(message = "Scheduled date must be today or in the future")
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
