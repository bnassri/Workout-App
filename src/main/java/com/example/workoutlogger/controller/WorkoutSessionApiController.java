package com.example.workoutlogger.controller;


import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.dto.WorkoutSessionSummaryDto;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.workoutlogger.domain.WorkoutSet;
import com.example.workoutlogger.dto.AddWorkoutSetRequest;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.UUID;


@RestController
@RequestMapping("/api/workout-sessions")
public class WorkoutSessionApiController {

    private final WorkoutSessionService service;

    public WorkoutSessionApiController(WorkoutSessionService service) {
        this.service = service;
    }

    @PostMapping("/workout-sessions/start")
    public String startSession() {
        WorkoutSession session = service.startSession();
        return "redirect:/workout-sessions/" + session.getId();
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

    @PutMapping("/api/workout-sessions/{id}/end")
    public ResponseEntity<Void> endWorkout(@PathVariable UUID id) {
        service.endSession(id);
        return ResponseEntity.ok().build();
    }


}
