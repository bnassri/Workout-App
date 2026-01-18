package com.example.workoutlogger.dto;
import com.example.workoutlogger.dto.ExerciseSummaryDto;
import java.time.Instant;
import java.util.List;

public class WorkoutSummaryView {

    private final Instant startTime;
    private final Instant endTime;
    private final long durationSeconds;
    private final int totalSets;
    private final double totalVolume;
    private final List<ExerciseSummaryDto> exercises;

    public WorkoutSummaryView(
            Instant startTime,
            Instant endTime,
            long durationSeconds,
            int totalSets,
            double totalVolume,
            List<ExerciseSummaryDto> exercises
    ) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationSeconds = durationSeconds;
        this.totalSets = totalSets;
        this.totalVolume = totalVolume;
        this.exercises = exercises;
    }

    public Instant getStartTime() { return startTime; }
    public Instant getEndTime() { return endTime; }
    public long getDurationSeconds() { return durationSeconds; }
    public int getTotalSets() { return totalSets; }
    public double getTotalVolume() { return totalVolume; }
    public List<ExerciseSummaryDto> getExercises() { return exercises; }
}

