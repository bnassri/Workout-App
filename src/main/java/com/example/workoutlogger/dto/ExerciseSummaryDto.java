package com.example.workoutlogger.dto;

public class ExerciseSummaryDto {
    /**
     * Aggregated stats for a single exercise
     * within a workout session.
     */

    private final String name;
    private final int sets;
    private final double volume;


    public ExerciseSummaryDto(String name, int sets, double volume) {
        this.name = name;
        this.sets = sets;
        this.volume = volume;

    }

    public String getName() {
        return name;
    }
    public int getSets() {
        return sets;
    }
    public double getVolume() {
        return volume;
    }

}
