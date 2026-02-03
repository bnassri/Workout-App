package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.domain.WorkoutSet;
import com.example.workoutlogger.dto.AddWorkoutSetRequest;
import com.example.workoutlogger.dto.WorkoutSessionSummaryDto;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/workout-sessions")
public class WorkoutSessionAPIController {

    private final WorkoutSessionService workoutSessionService;

    public WorkoutSessionAPIController(WorkoutSessionService workoutSessionService) {
        this.workoutSessionService = workoutSessionService;
    }

    @PostMapping("/start")
    public WorkoutSession startSession() {
        return workoutSessionService.startSession();
    }

    @GetMapping("/{sessionId}")
    public WorkoutSession getSession(@PathVariable UUID sessionId) {
        return workoutSessionService.getSession(sessionId);
    }

    @GetMapping("/{sessionId}/summary")
    public WorkoutSessionSummaryDto getSummary(@PathVariable UUID sessionId) {
        return workoutSessionService.getSessionSummary(sessionId);
    }

    @PostMapping("/{sessionId}/sets")
    public WorkoutSet addSet(
            @PathVariable UUID sessionId,
            @RequestBody AddWorkoutSetRequest request
    ) {
        return workoutSessionService.addSet(sessionId, request);
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<Void> endWorkout(@PathVariable UUID id) {
        workoutSessionService.endSession(id);
        return ResponseEntity.ok().build();
    }
}
