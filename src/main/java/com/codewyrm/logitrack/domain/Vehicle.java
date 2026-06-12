package com.codewyrm.logitrack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
public class Vehicle {
    @Id
    private UUID id;
    private String vin;
    private String licensePlate;
    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    @Setter
    private VehicleStatus vehicleStatus;

    @Setter
    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    protected Vehicle() {}

    public Vehicle(String vin, String licensePlate, String brand, String model) {
        this.id = UUID.randomUUID();
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.vehicleStatus = VehicleStatus.AVAILABLE;
    }
}
