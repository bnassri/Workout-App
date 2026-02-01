package com.example.workoutlogger.dto;

import java.time.Instant;
import java.util.UUID;

/**
 * Response DTO for WorkoutSet.
 * 
 * This DTO is used when returning workout sets as part of API responses,
 * avoiding circular references with WorkoutSession.
 */
public class WorkoutSetResponse {
    
    private UUID id;
    private String exerciseName;
    private int reps;
    private double weight;
    private Instant createdAt;
    
    // Default constructor for Jackson
    public WorkoutSetResponse() {
    }
    
    // Full constructor
    public WorkoutSetResponse(UUID id, String exerciseName, int reps, double weight, Instant createdAt) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.weight = weight;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getExerciseName() {
        return exerciseName;
    }
    
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    
    public int getReps() {
        return reps;
    }
    
    public void setReps(int reps) {
        this.reps = reps;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
