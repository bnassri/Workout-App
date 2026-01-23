package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Renders pages related to workout templates.
 */
@Controller
@RequestMapping("/templates")
public class WorkoutTemplatePageController {

    private final WorkoutTemplateService templateService;

    public WorkoutTemplatePageController(WorkoutTemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * Show all available workout templates.
     */
    @GetMapping
    public String templates(Model model) {
        List<WorkoutTemplate> templates = templateService.getAllTemplates();
        model.addAttribute("templates", templates);
        return "templates-list"; // updated to the new Thymeleaf file
    }


    // Show the form to create a new template
    @GetMapping("/create")
    public String createTemplateForm(Model model) {
        return "template-create";
    }

    // Handle form submission
    @PostMapping("/create")
    public String createTemplateSubmit(
            @RequestParam String name,
            @RequestParam String exerciseNames) {

        // Split comma-separated exercises
        List<String> exercises = List.of(exerciseNames.split(","))
                .stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        // Call the service to create the template
        templateService.createTemplate(name, exercises);

        // Redirect back to templates list
        return "redirect:/templates";
    }


}
