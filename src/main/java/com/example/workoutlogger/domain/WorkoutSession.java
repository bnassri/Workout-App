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

    /* ===============================
       Relationships
       =============================== */

    @OneToMany(
            mappedBy = "session",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkoutSet> sets = new ArrayList<>();

    /**
     * Every workout session can optionally belong to a template
     * (e.g. Chest, Arms, Legs).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private WorkoutTemplate template;

    /* ===============================
       Columns
       =============================== */

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, updatable = false, length = 36)
    private UUID id;

    @Column(nullable = false)
    private Instant startTime;

    private Instant endTime;

    @Column(nullable = false)
    private String status;

    /* ===============================
       Constructors
       =============================== */

    public WorkoutSession() {
        // JPA only
    }

    public WorkoutSession(UUID id, Instant startTime, String status) {
        this.id = id;
        this.startTime = startTime;
        this.status = status;
    }

    /* ===============================
       Domain behavior
       =============================== */

    public void end(Instant endTime) {
        this.endTime = endTime;
        this.status = "COMPLETED";
    }

    /* ===============================
       Getters / Setters
       =============================== */

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

    public List<WorkoutSet> getSets() {
        return sets;
    }

    public WorkoutTemplate getTemplate() {
        return template;
    }

    public void setTemplate(WorkoutTemplate template) {
        this.template = template;
    }
}
