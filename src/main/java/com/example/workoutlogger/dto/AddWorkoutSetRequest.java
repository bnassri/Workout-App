package com.example.workoutlogger.dto;

// DTOs protect domain from API changes
public class AddWorkoutSetRequest {

    private String exerciseName;
    private int reps;
    private double weight;

    public String getExerciseName() {
        return exerciseName;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    // setters
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }
    public void setReps(int reps) { this.reps = reps; }
    public void setWeight(double weight) { this.weight = weight; }
}
