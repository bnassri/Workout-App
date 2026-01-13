package com.example.workoutlogger.controller;


import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.dto.WorkoutSessionSummaryDto;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.workoutlogger.domain.WorkoutSet;
import com.example.workoutlogger.dto.AddWorkoutSetRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    /**
     * Live summary endpoint for a workout session.
     *
     * This is ideal for:
     * - Polling every few seconds
     * - Client-rendered UIs
     * - Future WebSocket updates
     */
    @GetMapping("/{sessionId}/summary")
    public WorkoutSessionSummaryDto getSummary(
            @PathVariable UUID sessionId
    ) {
        return service.getSessionSummary(sessionId);
    }

    /**
      Add a set to an existing workout session.
      Example:
      POST /api/workout-sessions/{sessionId}/sets
      Body:
      {
        "exerciseName": "Bench Press",
        "reps": 8,
        "weight": 80
     }
     **/
    @PostMapping("/{sessionId}/sets")
    public WorkoutSet addSet(
            @PathVariable UUID sessionId,
            @RequestBody AddWorkoutSetRequest request
    ) {
        return service.addSet(sessionId, request);
    }

}
