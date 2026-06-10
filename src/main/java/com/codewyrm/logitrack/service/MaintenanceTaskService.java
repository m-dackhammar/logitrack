package com.codewyrm.logitrack.service;

import com.codewyrm.logitrack.domain.MaintenanceTask;
import com.codewyrm.logitrack.domain.Vehicle;
import com.codewyrm.logitrack.dto.create.MaintenanceTaskCreateDTO;
import com.codewyrm.logitrack.dto.response.MaintenanceTaskResponseDTO;
import com.codewyrm.logitrack.repository.DriverRepo;
import com.codewyrm.logitrack.repository.MaintenanceTaskRepo;
import com.codewyrm.logitrack.repository.VehicleRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MaintenanceTaskService {
    private final MaintenanceTaskRepo maintenanceTaskRepo;
    private final VehicleRepo vehicleRepo;
    private final DriverRepo driverRepo;

    public MaintenanceTaskService(VehicleRepo vehicleRepo, DriverRepo driverRepo, MaintenanceTaskRepo maintenanceTaskRepo) {
        this.vehicleRepo = vehicleRepo;
        this.driverRepo = driverRepo;
        this.maintenanceTaskRepo = maintenanceTaskRepo;
    }

    public MaintenanceTaskResponseDTO scheduleMaintenance(MaintenanceTaskCreateDTO maintenanceTaskCreateDTO) {

        Vehicle vehicle =  vehicleRepo.findByLicensePlate(maintenanceTaskCreateDTO.licensePlate()).orElseThrow(() -> new EntityNotFoundException("Vehicle Not Found"));

        if (maintenanceTaskRepo.existsByVehicleAndCompletionDateIsNull(vehicle)) {
            throw new IllegalStateException("Vehicle Already Has an active Maintenance Task");
        }

        MaintenanceTask maintenanceTask = maintenanceTaskCreateDTO.toEntity(vehicle);
        maintenanceTaskRepo.save(maintenanceTask);

        return MaintenanceTaskResponseDTO.fromEntity(maintenanceTask);
    }

    public MaintenanceTaskResponseDTO completeMaintenanceTask(UUID taskId) {
        MaintenanceTask maintenanceTask = maintenanceTaskRepo.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Maintenance Task Not Found"));

        if (maintenanceTask.getCompletionDate() != null) {
            throw new IllegalStateException("Maintenance Task Already Completed");
        }

        maintenanceTask.setCompletionDate(LocalDate.now());
        maintenanceTaskRepo.save(maintenanceTask);

        return MaintenanceTaskResponseDTO.fromEntity(maintenanceTask);
    }

    public List<MaintenanceTaskResponseDTO> getVehicleMaintenanceHistory(String licensePlate) {
        List<MaintenanceTask> tasks = maintenanceTaskRepo.findByVehicleLicensePlate(licensePlate);

        return tasks.stream().map(MaintenanceTaskResponseDTO::fromEntity).toList();
    }
}
