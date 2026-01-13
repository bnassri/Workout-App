package com.example.workoutlogger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;

public class WorkoutSessionSummaryDto {
    /**
     * Read-only summary of a workout session.
     * This is optimized for UI consumption.
     */

    private final UUID sessionId;
    private final String status;
    private final int totalSets;
    private final double totalVolume;
    private final List<ExerciseSummaryDto> exercises;

    public WorkoutSessionSummaryDto(
            @JsonProperty("sessionId") UUID sessionId,
            @JsonProperty("status") String status,
            @JsonProperty("totalSets") int totalSets,
            @JsonProperty("totalVolume") double totalVolume,
            @JsonProperty("exercises") List<ExerciseSummaryDto> exercises
    ) {
        this.sessionId = sessionId;
        this.status = status;
        this.totalSets = totalSets;
        this.totalVolume = totalVolume;
        this.exercises = exercises;
    }

}
