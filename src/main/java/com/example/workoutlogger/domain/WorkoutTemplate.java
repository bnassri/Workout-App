package com.example.workoutlogger.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workout_template")
public class WorkoutTemplate {

    /**
     * Primary key (UUID stored as TEXT)
     */
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, updatable = false, length = 36)
    private UUID id;

    /**
     * Readable name (e.g. "Chest", "Push", "Leg Day")
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Exercises that belong to this template
     */
    @OneToMany(
            mappedBy = "template",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkoutTemplateExercise> exercises = new ArrayList<>();

    protected WorkoutTemplate() {
        // JPA only
    }

    public WorkoutTemplate(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    // ------------------
    // Getters
    // ------------------

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<WorkoutTemplateExercise> getExercises() {
        return exercises;
    }

    public void setName(String name) {
        this.name = name;
    }


    // ------------------
    // Helper methods
    // ------------------

    /**
     * Adds an exercise to this template
     * Keeps both sides of the relationship in sync
     */
    public void addExercise(String exerciseName) {
        exercises.add(new WorkoutTemplateExercise(this, exerciseName));
    }
}
