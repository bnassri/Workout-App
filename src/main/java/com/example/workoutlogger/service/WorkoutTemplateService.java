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

    public List<WorkoutTemplate> getAllTemplates() {
        return repository.findAll();
    }

    public WorkoutTemplate getTemplateById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template not found"));
    }

    /**
     * Update template name and exercises safely.
     */
    public void updateTemplate(UUID id, String name, List<String> exerciseNames) {
        WorkoutTemplate template = getTemplateById(id);

        // Update name
        template.setName(name);

        // Clear existing exercises (orphanRemoval = true deletes rows)
        template.getExercises().clear();

        // Re-add exercises
        if (exerciseNames != null) {
            exerciseNames.stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .forEach(template::addExercise);
        }

        repository.save(template);
    }
}
