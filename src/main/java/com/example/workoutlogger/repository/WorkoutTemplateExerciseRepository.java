package com.example.workoutlogger.repository;

import com.example.workoutlogger.domain.WorkoutTemplateExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutTemplateExerciseRepository extends JpaRepository<WorkoutTemplateExercise, UUID> {
}
