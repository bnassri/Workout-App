package com.example.workoutlogger.service;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.repository.WorkoutSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.workoutlogger.domain.WorkoutSet;
import com.example.workoutlogger.dto.AddWorkoutSetRequest;
import com.example.workoutlogger.repository.WorkoutSetRepository;
import com.example.workoutlogger.dto.*;
import com.example.workoutlogger.domain.WorkoutSet;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
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

    /**
     * Fetch a workout session by ID.
     *
     * This is used by:
     * - Live workout page
     * - Session summary view
     *
     * Throws if the session does not exist.
     */
    public WorkoutSession getSession(UUID sessionId) {
        return repository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
    }
    @Transactional
    public WorkoutSession endSession(UUID sessionId) {
        WorkoutSession session = repository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        session.end(Instant.now());

        return repository.save(session);
    }


    /**
     * Builds a live summary of a workout session.
     *
     * This method:
     * - Aggregates sets per exercise
     * - Calculates volume (reps * weight)
     * - Produces a UI-friendly DTO
     */
    public WorkoutSessionSummaryDto getSessionSummary(UUID sessionId) {
        System.out.println("DEBUG summary requested for sessionId = " + sessionId);
        WorkoutSession session = repository.findById(sessionId)
                .orElseThrow(() ->     new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Workout session not found"
                ));

        List<WorkoutSet> sets = session.getSets();

        // Group sets by exercise name
        Map<String, List<WorkoutSet>> byExercise =
                sets.stream().collect(Collectors.groupingBy(
                        WorkoutSet::getExerciseName
                ));

        List<ExerciseSummaryDto> exerciseSummaries = new ArrayList<>();

        double totalVolume = 0;

        for (Map.Entry<String, List<WorkoutSet>> entry : byExercise.entrySet()) {
            String exerciseName = entry.getKey();
            List<WorkoutSet> exerciseSets = entry.getValue();

            double exerciseVolume = exerciseSets.stream()
                    .mapToDouble(s -> s.getReps() * s.getWeight())
                    .sum();

            totalVolume += exerciseVolume;

            exerciseSummaries.add(
                    new ExerciseSummaryDto(
                            exerciseName,
                            exerciseSets.size(),
                            exerciseVolume
                    )
            );
        }

        return new WorkoutSessionSummaryDto(
                session.getId(),
                session.getStatus(),
                sets.size(),
                totalVolume,
                exerciseSummaries
        );


    }

    public WorkoutSummaryView getWorkoutSummaryView(UUID sessionId) {
        WorkoutSession session = repository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        if (session.getEndTime() == null) {
            throw new IllegalStateException("Workout has not ended yet");
        }

        long durationSeconds = Duration
                .between(session.getStartTime(), session.getEndTime())
                .getSeconds();

        int totalSets = session.getSets().size();

        double totalVolume = session.getSets().stream()
                .mapToDouble(s -> s.getReps() * s.getWeight())
                .sum();

        Map<String, List<WorkoutSet>> byExercise =
                session.getSets().stream()
                        .collect(Collectors.groupingBy(WorkoutSet::getExerciseName));

        List<ExerciseSummaryDto> exercises = byExercise.entrySet().stream()
                .map(e -> {
                    int sets = e.getValue().size();
                    double volume = e.getValue().stream()
                            .mapToDouble(s -> s.getReps() * s.getWeight())
                            .sum();
                    return new ExerciseSummaryDto(e.getKey(), sets, volume);
                })
                .toList();

        return new WorkoutSummaryView(
                session.getStartTime(),
                session.getEndTime(),
                durationSeconds,
                totalSets,
                totalVolume,
                exercises
        );
    }



}
