package com.codewyrm.logitrack.service;

import com.codewyrm.logitrack.domain.Driver;
import com.codewyrm.logitrack.domain.Vehicle;
import com.codewyrm.logitrack.dto.create.VehicleCreateDTO;
import com.codewyrm.logitrack.dto.response.VehicleResponseDTO;
import com.codewyrm.logitrack.repository.DriverRepo;
import com.codewyrm.logitrack.repository.VehicleRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepo vehicleRepo;
    private final DriverRepo driverRepo;

    public VehicleService(VehicleRepo vehicleRepo, DriverRepo driverRepo) {
        this.vehicleRepo = vehicleRepo;
        this.driverRepo = driverRepo;
    }

    public VehicleResponseDTO registerVehicle(VehicleCreateDTO vehicleCreateDTO) {
        if (vehicleRepo.existsByLicensePlate(vehicleCreateDTO.licensePlate())) {
            throw new IllegalStateException("Vehicle already exists");
        }
        Vehicle vehicle = vehicleCreateDTO.toEntity();
        vehicleRepo.save(vehicle);

        return VehicleResponseDTO.fromEntity(vehicle);
    }

    public VehicleResponseDTO getVehicleByPlate(String licensePlate) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licensePlate).orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        return VehicleResponseDTO.fromEntity(vehicle);
    }

    public VehicleResponseDTO assignDriver(String licensePlate, String employeeId) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));

        Driver driver = driverRepo.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Driver Not Found"));

        if (vehicleRepo.existsByDriver(driver)) {
            throw new IllegalStateException("Driver Already Assigned");
        }

        vehicle.setDriver(driver);
        vehicleRepo.save(vehicle);

        return VehicleResponseDTO.fromEntity(vehicle);
    }

    public VehicleResponseDTO releaseDriver(String licensePlate) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));

        vehicle.setDriver(null);
        vehicleRepo.save(vehicle);

        return VehicleResponseDTO.fromEntity(vehicle);
    }

}
