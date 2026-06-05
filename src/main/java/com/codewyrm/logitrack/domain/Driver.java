package com.codewyrm.logitrack.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Driver {
    @Id
    private UUID id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String licenseNumber;

    public Driver() {}

    public Driver(String employeeId, String firstName, String lastName, String licenseNumber) {
        this.id = UUID.randomUUID();
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
    }
}
