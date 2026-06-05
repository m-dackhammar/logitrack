package com.codewyrm.logitrack.service;

import com.codewyrm.logitrack.domain.Driver;
import com.codewyrm.logitrack.domain.Vehicle;
import com.codewyrm.logitrack.dto.VehicleCreateDTO;
import com.codewyrm.logitrack.dto.VehicleResponseDTO;
import com.codewyrm.logitrack.repository.DriverRepo;
import com.codewyrm.logitrack.repository.VehicleRepo;
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
        if (!vehicleRepo.existsByLicensePlate(vehicleCreateDTO.licensePlate())) {
            throw new RuntimeException("Vehicle already exists");
        }
        Vehicle vehicle = vehicleCreateDTO.toEntity();
        vehicleRepo.save(vehicle);

        return VehicleResponseDTO.fromEntity(vehicle);
    }

    public VehicleResponseDTO getVehicleByPlate(String licensePlate) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licensePlate).orElseThrow(() -> new RuntimeException("Vehicle not found"));

        return VehicleResponseDTO.fromEntity(vehicle);
    }

    public VehicleResponseDTO assignDriver(String licencePlate, String employeeId) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licencePlate)
                .orElseThrow(() -> new RuntimeException("Vehicle Not Found"));

        Driver driver = driverRepo.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Driver Not Found"));

        if (!vehicleRepo.existsByDriver(driver)) {
            throw new RuntimeException("Driver Already Assigned");
        }

        vehicle.setCurrentDriver(driver);
        vehicleRepo.save(vehicle);

        return VehicleResponseDTO.fromEntity(vehicle);
    }

    public VehicleResponseDTO releaseDriver(String licencePlate) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licencePlate)
                .orElseThrow(() -> new RuntimeException("Vehicle Not Found"));

        vehicle.setCurrentDriver(null);
        vehicleRepo.save(vehicle);

        return VehicleResponseDTO.fromEntity(vehicle);
    }

}
