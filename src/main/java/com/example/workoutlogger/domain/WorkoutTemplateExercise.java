package com.example.workoutlogger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.persistence.Id;


import java.util.UUID;

@Entity
public class WorkoutTemplateExercise {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "template_id")
    private WorkoutTemplate template;

    @Column(nullable = false)
    private String exerciseName;

    protected WorkoutTemplateExercise() {}

    public WorkoutTemplateExercise(WorkoutTemplate template, String exerciseName) {
        this.id = UUID.randomUUID();
        this.template = template;
        this.exerciseName = exerciseName;
    }

    // getters
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

