package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String showCreateForm() {
        return "create-template"; // corresponds to create-template.html
    }

    // Handle form submission
    @PostMapping("/create")
    public String createTemplate(String name, String exercises) {
        // Split exercises by comma and trim spaces
        List<String> exerciseList = Arrays.stream(exercises.split(","))
                .map(String::trim)
                .toList();

        // Call the service to create the template
        templateService.createTemplate(name, exerciseList);

        // Redirect back to templates list
        return "redirect:/templates";
    }


}
