package com.example.workoutlogger.controller;


import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
