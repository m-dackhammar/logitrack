package com.codewyrm.logitrack.controller;

import com.codewyrm.logitrack.domain.Driver;
import com.codewyrm.logitrack.dto.create.DriverCreateDTO;
import com.codewyrm.logitrack.dto.response.DriverResponseDTO;
import com.codewyrm.logitrack.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<DriverResponseDTO> getDriver(@PathVariable String employeeId) {
        DriverResponseDTO driverResponseDTO = driverService.getDriverByEmployeeId(employeeId);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @PostMapping
    public ResponseEntity<DriverResponseDTO> createDriver(@Valid @RequestBody DriverCreateDTO driverDto) {
        DriverResponseDTO responseDTO = driverService.registerDriver(driverDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
