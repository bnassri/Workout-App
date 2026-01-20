package com.example.workoutlogger.repository;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.domain.WorkoutTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutSessionRepository
        extends JpaRepository<WorkoutSession, UUID> {
    Optional<WorkoutSession>
    findTopByTemplateAndEndTimeBeforeOrderByEndTimeDesc(
            WorkoutTemplate template,
            Instant endTime
    );

}


