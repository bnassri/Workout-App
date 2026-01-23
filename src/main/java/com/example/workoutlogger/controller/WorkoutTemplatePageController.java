package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "TEMPLATES OK";
    }
    /**
     * Show all available workout templates.
     */
    @GetMapping
    public String templates(Model model) {
        // Fetch all templates to display
        List<WorkoutTemplate> templates = templateService.getAllTemplates();
        model.addAttribute("templates", templates);
        return "templates";
    }
}
