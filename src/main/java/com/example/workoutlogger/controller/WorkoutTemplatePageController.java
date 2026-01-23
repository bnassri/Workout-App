package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Renders pages related to workout templates.
 */
@Controller
public class WorkoutTemplatePageController {

    private final WorkoutTemplateService templateService;

    public WorkoutTemplatePageController(WorkoutTemplateService service) {
        this.templateService = service;
    }

    /**
     * Show all available workout templates.
     */
    @GetMapping("/templates")
    public String templates(Model model) {
        // Fetch all templates to display
        List<WorkoutTemplate> templates = templateService.getAllTemplates();
        model.addAttribute("templates", templates);
        return "home";
    }
}
