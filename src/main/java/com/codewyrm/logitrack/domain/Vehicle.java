package com.codewyrm.logitrack.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Vehicle {
    @Id
    private UUID id;
    private String vin;
    private String licensePlate;
    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver currentDriver;
}
