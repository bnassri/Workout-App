package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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
    @PostMapping("/edit/{id}")
    public String updateTemplate(
            @PathVariable UUID id,
            @RequestParam String name,
            @RequestParam(required = false) List<String> exerciseNames
    ) {
        templateService.updateTemplate(id, name, exerciseNames);
        return "redirect:/templates";
    }

    @GetMapping("/{id}")
    public String viewTemplate(
            @PathVariable UUID id,
            Model model
    ) {
        WorkoutTemplate template = templateService.getTemplateById(id);
        model.addAttribute("template", template);
        return "template-detail";
    }


    @GetMapping("/edit/{id}")
    public String editTemplate(@PathVariable UUID id, Model model) {
        WorkoutTemplate template = templateService.getTemplateById(id);

        model.addAttribute("template", template);

        return "edit-template"; // <-- template-edit.html
    }
    @PostMapping("/create")
    public String createTemplate(
            @RequestParam String name
    ) {
        WorkoutTemplate template = templateService.createTemplate(name);
        return "redirect:/templates/" + template.getId();
    }

}
