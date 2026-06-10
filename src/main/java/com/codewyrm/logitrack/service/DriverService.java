package com.codewyrm.logitrack.service;

import com.codewyrm.logitrack.domain.Driver;
import com.codewyrm.logitrack.dto.create.DriverCreateDTO;
import com.codewyrm.logitrack.dto.response.DriverResponseDTO;
import com.codewyrm.logitrack.repository.DriverRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    private final DriverRepo driverRepo;

    public DriverService(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    public DriverResponseDTO getDriverByEmployeeId(String employeeId) {
        Driver driver = driverRepo.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("failed to get driver by employeeId"));

        return DriverResponseDTO.fromEntity(driver);
    }

    public DriverResponseDTO registerDriver(DriverCreateDTO driverDto) {
        Driver newDriver = driverDto.toEntity();
        Driver savedDriver = driverRepo.save(newDriver);
        return DriverResponseDTO.fromEntity(savedDriver);
    }
}
