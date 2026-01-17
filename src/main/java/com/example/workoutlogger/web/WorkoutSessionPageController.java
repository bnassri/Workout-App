package com.example.workoutlogger.web;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class WorkoutSessionPageController {

    private final WorkoutSessionService service;

    public WorkoutSessionPageController(WorkoutSessionService service) {
        this.service = service;
    }

    /**
     * Handles the Start Workout button from home.html
     */
    @PostMapping("/workout-sessions/start")
    public String startWorkout() {
        WorkoutSession session = service.startSession();
        return "redirect:/workout-sessions/" + session.getId();
    }

    /**
     * Renders the workout session page
     */
    @GetMapping("/workout-sessions/{id}")
    public String workoutSessionPage(
            @PathVariable UUID id,
            Model model
    ) {
        WorkoutSession session = service.getSession(id);

        model.addAttribute("session", session);
        model.addAttribute("sessionId", session.getId());
        model.addAttribute(
                "startTimeMillis",
                session.getStartTime().toEpochMilli()

        );

        return "workout-session";
    }

}
