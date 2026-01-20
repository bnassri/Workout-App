package com.example.workoutlogger.repository;
import com.example.workoutlogger.domain.WorkoutTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface WorkoutTemplateRepository extends JpaRepository<WorkoutTemplate, UUID> {
}
