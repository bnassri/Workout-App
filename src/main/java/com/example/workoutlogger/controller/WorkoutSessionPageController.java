package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.service.WorkoutSessionService;
import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/workout-sessions")
public class WorkoutSessionPageController {

    private final WorkoutSessionService sessionService;
    private final WorkoutTemplateService templateService;

    public WorkoutSessionPageController(
            WorkoutSessionService sessionService,
            WorkoutTemplateService templateService
    ) {
        this.sessionService = sessionService;
        this.templateService = templateService;
    }

    /**
     * Render the start workout page (optional: show template selection)
     */
    @GetMapping("/start")
    public String startWorkoutPage(Model model) {
        List<WorkoutTemplate> templates = templateService.getAllTemplates();
        model.addAttribute("templates", templates);
        return "start-workout"; // a Thymeleaf page with a form to select template or start ad-hoc
    }

    /**
     * Start a workout session
     */
    @PostMapping("/start")
    public String startWorkout(
            @RequestParam(required = false) UUID templateId
    ) {
        WorkoutSession session;

        if (templateId != null) {
            WorkoutTemplate template = templateService.getTemplateById(templateId);
            session = sessionService.startSession(template);
        } else {
            session = sessionService.startSession();
        }

        return "redirect:/workout-sessions/" + session.getId();
    }

    /**
     * Renders the workout session page
     */
    @GetMapping("/{id}")
    public String workoutSessionPage(
            @PathVariable UUID id,
            Model model
    ) {
        WorkoutSession session = sessionService.getSession(id);

        model.addAttribute("session", session);
        model.addAttribute("sessionId", session.getId());
        model.addAttribute(
                "startTimeMillis",
                session.getStartTime().toEpochMilli()
        );

        return "workout-session";
    }
}
