package com.codewyrm.logitrack.service;

import com.codewyrm.logitrack.domain.Driver;
import com.codewyrm.logitrack.domain.Vehicle;
import com.codewyrm.logitrack.dto.create.VehicleCreateDTO;
import com.codewyrm.logitrack.dto.response.VehicleResponseDTO;
import com.codewyrm.logitrack.repository.DriverRepo;
import com.codewyrm.logitrack.repository.VehicleRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepo vehicleRepo;
    @Mock
    private DriverRepo driverRepo;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void registerVehicle_ThrowsException_WhenVehicleExists() {
        VehicleCreateDTO dto = new VehicleCreateDTO("12345678901234567", "XYZ-123", "Volvo", "FH16");

        when(vehicleRepo.existsByLicensePlate(dto.licensePlate())).thenReturn(true);

        assertThatThrownBy(() -> vehicleService.registerVehicle(dto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Vehicle already exists");
        verify(vehicleRepo, never()).save(any(Vehicle.class));
    }

    @Test
    void registerVehicle_IsSuccessful_WhenVehicleDoesNotExist() {
        VehicleCreateDTO dto = new VehicleCreateDTO("12345678901234567", "XYZ-123", "Volvo", "FH16");

        when(vehicleRepo.existsByLicensePlate(dto.licensePlate())).thenReturn(false);

        VehicleResponseDTO responseDTO = vehicleService.registerVehicle(dto);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.licensePlate()).isEqualTo(dto.licensePlate());
        assertThat(responseDTO.brand()).isEqualTo(dto.brand());

        verify(vehicleRepo, times(1)).save(any(Vehicle.class));
    }

    @Test
    void getVehicleByPlate_ThrowsException_WhenVehicleDoesNotExist() {
        String licensePlate = "XYZ-123";

        when(vehicleRepo.findByLicensePlate(licensePlate)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehicleService.getVehicleByPlate(licensePlate))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Vehicle not found");

        verify(vehicleRepo, times(1)).findByLicensePlate(licensePlate);
    }

    @Test
    void getVehicleByPlate_ReturnsResponseDto_WhenVehicleDoes() {

        Vehicle vehicle = new Vehicle("12345678901234567", "XYZ-123", "Volvo", "FH16");

        when(vehicleRepo.findByLicensePlate(vehicle.getLicensePlate())).thenReturn(Optional.of(vehicle));

        VehicleResponseDTO responseDTO = vehicleService.getVehicleByPlate(vehicle.getLicensePlate());

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.licensePlate()).isEqualTo(vehicle.getLicensePlate());
        assertThat(responseDTO.brand()).isEqualTo(vehicle.getBrand());

        verify(vehicleRepo, times(1)).findByLicensePlate(vehicle.getLicensePlate());
    }

    @Test
    void assignDriver_ThrowsException_WhenVeicleDoesNotExist() {
        Driver driver = new Driver("DRV-101", "John", "Doe", "1234567890");
        Vehicle vehicle = new Vehicle("12345678901234567", "XYZ-123", "Volvo", "FH16");

        when(vehicleRepo.findByLicensePlate(vehicle.getLicensePlate())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehicleService.assignDriver(vehicle.getLicensePlate(), driver.getEmployeeId()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Vehicle Not Found");

        verify(vehicleRepo, times(1)).findByLicensePlate(vehicle.getLicensePlate());
    }

    @Test
    void assignDriver_ThrowsException_WhenDriverDoesNotExist() {
        Driver driver = new Driver("DRV-101", "John", "Doe", "1234567890");
        Vehicle vehicle = new Vehicle("12345678901234567", "XYZ-123", "Volvo", "FH16");

        when(vehicleRepo.findByLicensePlate(vehicle.getLicensePlate())).thenReturn(Optional.of(vehicle));
        when(driverRepo.findByEmployeeId(driver.getEmployeeId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehicleService.assignDriver(vehicle.getLicensePlate(), driver.getEmployeeId()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Driver Not Found");

        verify(driverRepo, times(1)).findByEmployeeId(driver.getEmployeeId());
        verify(vehicleRepo, times(1)).findByLicensePlate(vehicle.getLicensePlate());
    }

    @Test
    void assignDriver_ThrowsException_WhenDriverIsAlreadyAssigned() {
        Driver driver = new Driver("DRV-101", "John", "Doe", "1234567890");
        Vehicle vehicle = new Vehicle("12345678901234567", "XYZ-123", "Volvo", "FH16");

        when(vehicleRepo.findByLicensePlate(vehicle.getLicensePlate())).thenReturn(Optional.of(vehicle));
        when(driverRepo.findByEmployeeId(driver.getEmployeeId())).thenReturn(Optional.of(driver));
        when(vehicleRepo.existsByDriver(driver)).thenReturn(true);

        assertThatThrownBy(() -> vehicleService.assignDriver(vehicle.getLicensePlate(), driver.getEmployeeId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Driver Already Assigned");

        verify(driverRepo, times(1)).findByEmployeeId(driver.getEmployeeId());
        verify(vehicleRepo, times(1)).findByLicensePlate(vehicle.getLicensePlate());
    }

    @Test
    void assignDriver_HappyPath() {
        Driver driver = new Driver("DRV-101", "John", "Doe", "1234567890");
        Vehicle vehicle = new Vehicle("12345678901234567", "XYZ-123", "Volvo", "FH16");

        when(vehicleRepo.findByLicensePlate(vehicle.getLicensePlate())).thenReturn(Optional.of(vehicle));
        when(driverRepo.findByEmployeeId(driver.getEmployeeId())).thenReturn(Optional.of(driver));
        when(vehicleRepo.existsByDriver(driver)).thenReturn(false);

        VehicleResponseDTO responseDTO = vehicleService.assignDriver(vehicle.getLicensePlate(), driver.getEmployeeId());

        assertThat(responseDTO.driverEmployeeId()).isEqualTo(driver.getEmployeeId());

        verify(vehicleRepo, times(1)).save(vehicle);
    }

    @Test
    void releaseDriver_ThrowsException_WhenVehicleDoesNotExist() {

    }
}