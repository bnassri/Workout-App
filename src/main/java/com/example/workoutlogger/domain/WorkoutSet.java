package com.example.workoutlogger.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "workout_set")
public class WorkoutSet {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "session_id")
    private WorkoutSession session;

    @Column(nullable = false)
    private String exerciseName;

    @Column(nullable = false)
    private int reps;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private Instant createdAt;

    protected WorkoutSet() {
        // JPA
    }

    public WorkoutSet(
            UUID id,
            WorkoutSession session,
            String exerciseName,
            int reps,
            double weight,
            Instant createdAt
    ) {
        this.id = id;
        this.session = session;
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.weight = weight;
        this.createdAt = createdAt;
    }

    // Getters
    public UUID getId() { return id; }
    public WorkoutSession getSession() { return session; }
    public String getExerciseName() { return exerciseName; }
    public int getReps() { return reps; }
    public double getWeight() { return weight; }
    public Instant getCreatedAt() { return createdAt; }
}
