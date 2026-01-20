package com.example.workoutlogger.dto;

import java.util.*;


import java.util.List;

public record WorkoutComparisonDto(
        double previousTotalVolume,
        double currentTotalVolume,
        double totalDelta,
        List<ExerciseComparison> exercises
) {

    public record ExerciseComparison(
            String exercise,
            double previousVolume,
            double currentVolume,
            double delta
    ) {}
}
