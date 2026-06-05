package com.codewyrm.logitrack.controller;

import com.codewyrm.logitrack.dto.VehicleCreateDTO;
import com.codewyrm.logitrack.dto.VehicleResponseDTO;
import com.codewyrm.logitrack.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> register(@RequestBody VehicleCreateDTO vehicleCreateDTO) {
        VehicleResponseDTO responseDTO = vehicleService.registerVehicle(vehicleCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<VehicleResponseDTO> getVehicle(@PathVariable String licensePlate) {
        VehicleResponseDTO responseDTO = vehicleService.getVehicleByPlate(licensePlate);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{licensePlate}/driver/{employeeId}")
    public ResponseEntity<VehicleResponseDTO> assignDriver(@PathVariable String licensePlate, @PathVariable String employeeId) {
        VehicleResponseDTO responseDTO = vehicleService.assignDriver(licensePlate, employeeId);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{licensePlate}/driver")
    public ResponseEntity<VehicleResponseDTO> releaseDriver(@PathVariable String licensePlate) {
        VehicleResponseDTO responseDTO = vehicleService.releaseDriver(licensePlate);
        return ResponseEntity.ok(responseDTO);
    }
}
