package com.example.workoutlogger.dto;
import com.example.workoutlogger.domain.WorkoutSet;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class WorkoutSummaryView {

    private final Instant startTime;
    private final Instant endTime;
    private final long durationSeconds;
    private final int totalSets;
    private final double totalVolume;
    private final List<ExerciseSummaryDto> exercises;
    private final String notes;
    private final Map<String, List<WorkoutSet>> setsByExercise;

    public WorkoutSummaryView(
            Instant startTime,
            Instant endTime,
            long durationSeconds,
            int totalSets,
            double totalVolume,
            List<ExerciseSummaryDto> exercises,
            String notes,
            Map<String, List<WorkoutSet>> setsByExercise
    ) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationSeconds = durationSeconds;
        this.totalSets = totalSets;
        this.totalVolume = totalVolume;
        this.exercises = exercises;
        this.notes = notes;
        this.setsByExercise = setsByExercise;
    }

    public Instant getStartTime() { return startTime; }
    public Instant getEndTime() { return endTime; }
    public long getDurationSeconds() { return durationSeconds; }
    public int getTotalSets() { return totalSets; }
    public double getTotalVolume() { return totalVolume; }
    public List<ExerciseSummaryDto> getExercises() { return exercises; }
    public String getNotes() { return notes; }
    public Map<String, List<WorkoutSet>> getSetsByExercise() { return setsByExercise; }
}