package com.example.workoutlogger.repository;

import com.example.workoutlogger.domain.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutSessionRepository
        extends JpaRepository<WorkoutSession, UUID> {
}
