package com.codewyrm.logitrack.repository;

import com.codewyrm.logitrack.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepo extends JpaRepository<Driver, UUID> {
    Optional<Driver> findByEmployeeId(String employeeId);
}