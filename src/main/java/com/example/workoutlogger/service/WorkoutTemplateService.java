package com.example.workoutlogger.service;


import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.domain.WorkoutTemplateExercise;
import com.example.workoutlogger.repository.WorkoutTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class WorkoutTemplateService {
    private final WorkoutTemplateRepository repository;

    public WorkoutTemplateService(WorkoutTemplateRepository repository) {
        this.repository = repository;
    }

    /**
     * Create a new workout template (e.g. "Chest Day").
     */
    public WorkoutTemplate createTemplate(String name, List<String> exerciseNames) {
        WorkoutTemplate template = new WorkoutTemplate(name);

        for (String exerciseName : exerciseNames) {
            template.addExercise(exerciseName);
        }

        return repository.save(template);
    }


    /**
     * Fetch all workout templates.
     * Used for selection UI.
     */
    public List<WorkoutTemplate> getAllTemplates() {
        return repository.findAll();
    }

    /**
     * Fetch a single template.
     */
    public WorkoutTemplate getTemplate(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template not found"));
    }
}
