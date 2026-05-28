package com.codewyrm.logitrack.domain;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MaintenanceTask {
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private String description;
    private BigDecimal cost;
    private LocalDate scheduledDate;
    private LocalDate completionDate;
}
