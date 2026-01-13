package com.example.workoutlogger.repository;

import com.example.workoutlogger.domain.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutSetRepository
        extends JpaRepository<WorkoutSet, UUID> {
}
