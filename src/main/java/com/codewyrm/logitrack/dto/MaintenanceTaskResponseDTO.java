package com.codewyrm.logitrack.dto;

import com.codewyrm.logitrack.domain.Driver;
import com.codewyrm.logitrack.domain.MaintenanceTask;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record MaintenanceTaskResponseDTO(
    UUID id,
    String description,
    BigDecimal cost,
    LocalDate scheduledDate,
    LocalDate completionDate
) {
    public static MaintenanceTaskResponseDTO fromEntity(MaintenanceTask task) {
        return new MaintenanceTaskResponseDTO(
            task.getId(),
            task.getDescription(),
            task.getCost(),
            task.getScheduledDate(),
            task.getCompletionDate()
        );
    }
}
