package com.example.workoutlogger.service;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.domain.WorkoutSet;
import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.dto.WorkoutComparisonDto;
import com.example.workoutlogger.repository.WorkoutSessionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkoutComparisonService {

    private final WorkoutSessionRepository sessionRepository;

    public WorkoutComparisonService(WorkoutSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Compare a completed workout session to the previous one
     * that used the same template.
     */
    public WorkoutComparisonDto compareToPrevious(UUID currentSessionId) {

        WorkoutSession current = sessionRepository.findById(currentSessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        WorkoutTemplate template = current.getTemplate();
        if (template == null) {
            throw new IllegalStateException("Session has no template");
        }

        WorkoutSession previous = sessionRepository
                .findTopByTemplateAndEndTimeBeforeOrderByEndTimeDesc(
                        template,
                        current.getEndTime()
                )
                .orElseThrow(() -> new IllegalStateException("No previous workout"));

        Map<String, Double> prevVolumes = aggregateVolume(previous);
        Map<String, Double> currVolumes = aggregateVolume(current);

        Set<String> allExercises = new HashSet<>();
        allExercises.addAll(prevVolumes.keySet());
        allExercises.addAll(currVolumes.keySet());

        List<WorkoutComparisonDto.ExerciseComparison> comparisons = new ArrayList<>();

        for (String exercise : allExercises) {
            double prev = prevVolumes.getOrDefault(exercise, 0.0);
            double curr = currVolumes.getOrDefault(exercise, 0.0);

            comparisons.add(
                    new WorkoutComparisonDto.ExerciseComparison(
                            exercise,
                            prev,
                            curr,
                            curr - prev
                    )
            );
        }

        double prevTotal = prevVolumes.values().stream().mapToDouble(Double::doubleValue).sum();
        double currTotal = currVolumes.values().stream().mapToDouble(Double::doubleValue).sum();

        return new WorkoutComparisonDto(
                prevTotal,
                currTotal,
                currTotal - prevTotal,
                comparisons
        );
    }

    /**
     * Aggregate volume per exercise:
     * reps × weight
     */
    private Map<String, Double> aggregateVolume(WorkoutSession session) {
        Map<String, Double> result = new HashMap<>();

        for (WorkoutSet set : session.getSets()) {
            result.merge(
                    set.getExerciseName(),
                    set.getReps() * set.getWeight(),
                    Double::sum
            );
        }

        return result;
    }
}