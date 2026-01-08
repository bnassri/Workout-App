package com.example.workoutlogger.controller;


import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;


@RestController
@RequestMapping("/api/workout-sessions")
public class WorkoutSessionApiController {

    private final WorkoutSessionService service;

    public WorkoutSessionApiController(WorkoutSessionService service) {
        this.service = service;
    }

    @PostMapping("/start")
    public WorkoutSession startSession() {
        return service.startSession();
    }

    /**
     * Fetch a workout session along with its sets.
     *
     * Example:
     * GET /api/workout-sessions/{id}
     */
    @GetMapping("/{sessionId}")
    public WorkoutSession getSession(@PathVariable UUID sessionId) {
        return service.getSession(sessionId);
    }

}
