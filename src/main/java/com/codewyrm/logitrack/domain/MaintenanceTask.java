package com.codewyrm.logitrack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
public class MaintenanceTask {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private String description;
    private BigDecimal cost;
    private LocalDate scheduledDate;

    @Setter
    private LocalDate completionDate;

    protected MaintenanceTask() {}

    public MaintenanceTask(Vehicle vehicle, String description, BigDecimal cost, LocalDate scheduledDate) {
        this.vehicle = vehicle;
        this.description = description;
        this.cost = cost;
        this.scheduledDate =  scheduledDate;
        this.completionDate = null;
    }

}
