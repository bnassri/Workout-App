package com.example.workoutlogger.controller;

import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Renders pages related to workout templates.
 */
@Controller
public class WorkoutTemplatePageController {

    private final WorkoutTemplateService service;

    public WorkoutTemplatePageController(WorkoutTemplateService service) {
        this.service = service;
    }

    /**
     * Show all available workout templates.
     */
    @GetMapping("/workouts")
    public String workoutTemplates(Model model) {
        model.addAttribute("templates", service.getAllTemplates());
        return "workout-templates";
    }
}
