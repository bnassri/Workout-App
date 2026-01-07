package com.example.workoutlogger.service;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.repository.WorkoutSessionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class WorkoutSessionService {

    private final WorkoutSessionRepository repository;

    public WorkoutSessionService(WorkoutSessionRepository repository) {
        this.repository = repository;
    }

    public WorkoutSession startSession() {
        WorkoutSession session = new WorkoutSession(
                UUID.randomUUID(),
                Instant.now(),
                "IN_PROGRESS"
        );
        return repository.save(session);
    }
}
