package com.example.workoutlogger.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;



@Entity
@Table(name = "workout_session")
public class WorkoutSession {

    /**
     * One workout session can have many sets.
     *
     * mappedBy = "session" tells JPA:
     * - The foreign key lives in WorkoutSet.session
     * - WorkoutSession does NOT own the relationship
     *
     * cascade = ALL:
     * - If we delete a session, its sets are deleted
     *
     * orphanRemoval = true:
     * - Prevents "dangling" sets if removed from the session
     */
    @OneToMany(
            mappedBy = "session",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkoutSet> sets = new ArrayList<>();


    /**
     * Store UUID as TEXT instead of BLOB.
     * This makes IDs human-readable and API-friendly.
     */
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, updatable = false, length = 36)
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

    public List<WorkoutSet> getSets() {
        return sets;
    }
}
