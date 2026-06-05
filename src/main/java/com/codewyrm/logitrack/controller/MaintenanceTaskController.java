package com.codewyrm.logitrack.controller;

import com.codewyrm.logitrack.dto.MaintenanceTaskCreateDTO;
import com.codewyrm.logitrack.dto.MaintenanceTaskResponseDTO;
import com.codewyrm.logitrack.service.MaintenanceTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/maintenancetasks")
public class MaintenanceTaskController {
    private MaintenanceTaskService maintenanceTaskService;

    public MaintenanceTaskController(MaintenanceTaskService maintenanceTaskService) {
        this.maintenanceTaskService = maintenanceTaskService;
    }

    @PostMapping
    public ResponseEntity<MaintenanceTaskResponseDTO> scheduleMaintentance(@RequestBody MaintenanceTaskCreateDTO createDTO) {
        MaintenanceTaskResponseDTO responseDTO = maintenanceTaskService.scheduleMaintenance(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<MaintenanceTaskResponseDTO> completeMaintenance(@PathVariable UUID taskId) {
        MaintenanceTaskResponseDTO responseDTO = maintenanceTaskService.completeMaintenanceTask(taskId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/vehicle/{licensePlate}")
    public ResponseEntity<List<MaintenanceTaskResponseDTO>> getMaintenanceHistory(@PathVariable String licensePlate) {
        List<MaintenanceTaskResponseDTO> responseDTOList = maintenanceTaskService.getVehicleMaintenanceHistory(licensePlate);
        return ResponseEntity.ok(responseDTOList);
    }
}
