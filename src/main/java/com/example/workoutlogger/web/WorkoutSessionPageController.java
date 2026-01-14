package com.example.workoutlogger.web;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
@Controller
public class WorkoutSessionPageController {
    /**
     * Server-rendered pages for workout sessions.  Will return HTML, not JSON
     **/
    private final WorkoutSessionService service;

    public WorkoutSessionPageController(WorkoutSessionService service) {
        this.service = service;
    }

    /**
     * Live workout page
     * URL: /workout-sessions/{id}
     */

    @GetMapping("/workout-sessions/{sessionId}")
    public String workoutSessionPage(
            @PathVariable UUID sessionId,
            Model model) {
        WorkoutSession session = service.getSession(sessionId);

        // Expose session ID to the HTML page
        model.addAttribute("sessionId", session.getId());

        return "workout-session";
    }
}
