package com.example.workoutlogger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import java.util.List;
import java.util.UUID;

public class WorkoutSessionSummaryDto {
    /**
     * Read-only summary of a workout session.
     * This is optimized for UI consumption.
     * Serialized via fields (not setters).
     */

    private final UUID sessionId;
    private final String status;
    private final int totalSets;
    private final double totalVolume;
    private final List<ExerciseSummaryDto> exercises;

    public WorkoutSessionSummaryDto(
            UUID sessionId,
            String status,
            int totalSets,
            double totalVolume,
            List<ExerciseSummaryDto> exercises
    ) {
        this.sessionId = sessionId;
        this.status = status;
        this.totalSets = totalSets;
        this.totalVolume = totalVolume;
        this.exercises = exercises;
    }

}
