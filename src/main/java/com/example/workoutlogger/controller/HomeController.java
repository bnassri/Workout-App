package com.example.workoutlogger.controller;

import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Service dependency
    private final WorkoutTemplateService templateService;

    // Spring will inject this automatically
    public HomeController(WorkoutTemplateService templateService) {
        this.templateService = templateService;
    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("templates", templateService.getAllTemplates());
        return "home";
    }

}