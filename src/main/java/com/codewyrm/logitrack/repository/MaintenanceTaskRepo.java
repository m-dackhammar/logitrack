package com.codewyrm.logitrack.repository;

import com.codewyrm.logitrack.domain.MaintenanceTask;
import com.codewyrm.logitrack.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MaintenanceTaskRepo extends JpaRepository<MaintenanceTask, UUID> {
    boolean existsByVehicleAndCompletionDateIsNull(Vehicle vehicle);
    List<MaintenanceTask> findByVehicleLicensePlate(String vehicleLicensePlate);
}