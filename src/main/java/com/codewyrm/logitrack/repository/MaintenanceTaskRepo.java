package com.codewyrm.logitrack.repository;

import com.codewyrm.logitrack.domain.MaintenanceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaintenanceTaskRepo extends JpaRepository<MaintenanceTask, UUID> {
}