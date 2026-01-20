package com.example.workoutlogger.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "workout_template_exercise")
public class WorkoutTemplateExercise {

    /**
     * Primary key
     */
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, updatable = false, length = 36)
    private UUID id;

    /**
     * “Every workout must be based on a predefined workout (Chest, Arms, etc.)”
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "template_id")
    private WorkoutTemplate template;

    /**
     * Exercise name (free-form, same as WorkoutSet.exerciseName)
     */
    @Column(nullable = false)
    private String exerciseName;

    protected WorkoutTemplateExercise() {
        // JPA only
    }

    public WorkoutTemplateExercise(WorkoutTemplate template, String exerciseName) {
        this.id = UUID.randomUUID();
        this.template = template;
        this.exerciseName = exerciseName;
    }

    // ------------------
    // Getters
    // ------------------

    public UUID getId() {
        return id;
    }

    public WorkoutTemplate getTemplate() {
        return template;
    }

    public String getExerciseName() {
        return exerciseName;
    }
}
