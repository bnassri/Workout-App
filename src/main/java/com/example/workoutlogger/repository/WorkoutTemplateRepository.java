package com.example.workoutlogger.repository;

import com.example.workoutlogger.domain.WorkoutTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Persistence layer for WorkoutTemplate.
 * Handles basic CRUD operations.
 */
public interface WorkoutTemplateRepository extends JpaRepository<WorkoutTemplate, UUID> {

}
