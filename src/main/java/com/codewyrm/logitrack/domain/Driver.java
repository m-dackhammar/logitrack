package com.codewyrm.logitrack.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Driver {
    @Id
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String licenseNumber;

    public Driver() {}

    public Driver(Long id, String employeeId, String firstName, String lastName, String licenseNumber) {
        this.id = id;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}
