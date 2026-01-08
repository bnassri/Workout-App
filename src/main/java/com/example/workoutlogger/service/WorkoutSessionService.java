package com.example.workoutlogger.service;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.repository.WorkoutSessionRepository;
import org.springframework.stereotype.Service;
import com.example.workoutlogger.domain.WorkoutSet;
import com.example.workoutlogger.dto.AddWorkoutSetRequest;
import com.example.workoutlogger.repository.WorkoutSetRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class WorkoutSessionService {

    private final WorkoutSessionRepository repository;
    private final WorkoutSetRepository setRepository;

    public WorkoutSessionService(
            WorkoutSessionRepository repository,
            WorkoutSetRepository setRepository
    ) {
        this.repository = repository;
        this.setRepository = setRepository;
    }

    public WorkoutSession startSession() {
        WorkoutSession session = new WorkoutSession(
                UUID.randomUUID(),
                Instant.now(),
                "IN_PROGRESS"
        );
        return repository.save(session);
    }


    public WorkoutSet addSet(UUID sessionId, AddWorkoutSetRequest request) {
        WorkoutSession session = repository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        WorkoutSet set = new WorkoutSet(
                UUID.randomUUID(),
                session,
                request.getExerciseName(),
                request.getReps(),
                request.getWeight(),
                Instant.now()
        );

        return setRepository.save(set);
    }

}
