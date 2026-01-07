package com.example.workoutlogger.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "workout_session")
public class WorkoutSession {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private Instant startTime;

    private Instant endTime;

    @Column(nullable = false)
    private String status;

    protected WorkoutSession() {
        // JPA only
    }

    public WorkoutSession(UUID id, Instant startTime, String status) {
        this.id = id;
        this.startTime = startTime;
        this.status = status;
    }

    // Getters only (immutability-lite)
    public UUID getId() {
        return id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public void end(Instant endTime) {
        this.endTime = endTime;
        this.status = "COMPLETED";
    }
}
