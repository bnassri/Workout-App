package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.domain.WorkoutSet;
import com.example.workoutlogger.dto.*;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workout-sessions")
public class WorkoutSessionAPIController {

    private final WorkoutSessionService workoutSessionService;

    public WorkoutSessionAPIController(WorkoutSessionService workoutSessionService) {
        this.workoutSessionService = workoutSessionService;
    }

    /**
     * Start a new workout session.
     * Returns a WorkoutSessionResponse DTO to avoid circular reference issues.
     */
    @PostMapping("/start")
    public WorkoutSessionResponse startSession() {
        WorkoutSession session = workoutSessionService.startSession();
        return toResponse(session);
    }

    /**
     * Get a specific workout session by ID.
     * Returns a WorkoutSessionResponse DTO to avoid circular reference issues.
     */
    @GetMapping("/{sessionId}")
    public WorkoutSessionResponse getSession(@PathVariable UUID sessionId) {
        WorkoutSession session = workoutSessionService.getSession(sessionId);
        return toResponse(session);
    }

    /**
     * Get a summary of a workout session (aggregated by exercise).
     */
    @GetMapping("/{sessionId}/summary")
    public WorkoutSessionSummaryDto getSummary(@PathVariable UUID sessionId) {
        return workoutSessionService.getSessionSummary(sessionId);
    }

    /**
     * Add a new set to a workout session.
     * Returns a WorkoutSetResponse DTO to avoid exposing the full session.
     */
    @PostMapping("/{sessionId}/sets")
    public WorkoutSetResponse addSet(
            @PathVariable UUID sessionId,
            @RequestBody AddWorkoutSetRequest request
    ) {
        WorkoutSet set = workoutSessionService.addSet(sessionId, request);
        return toSetResponse(set);
    }

    /**
     * End a workout session.
     */
    @PutMapping("/{id}/end")
    public ResponseEntity<Void> endWorkout(@PathVariable UUID id) {
        workoutSessionService.endSession(id);
        return ResponseEntity.ok().build();
    }

    // ========== Helper Methods for DTO Mapping ==========

    /**
     * Convert a WorkoutSession entity to a WorkoutSessionResponse DTO.
     */
    private WorkoutSessionResponse toResponse(WorkoutSession session) {
        List<WorkoutSetResponse> setResponses = session.getSets().stream()
                .map(this::toSetResponse)
                .collect(Collectors.toList());

        WorkoutSessionResponse response = new WorkoutSessionResponse(
                session.getId(),
                session.getStartTime(),
                session.getEndTime(),
                session.getStatus(),
                setResponses
        );

        // Add template info if present
        if (session.getTemplate() != null) {
            response.setTemplateId(session.getTemplate().getId());
            response.setTemplateName(session.getTemplate().getName());
        }

        return response;
    }

    /**
     * Convert a WorkoutSet entity to a WorkoutSetResponse DTO.
     */
    private WorkoutSetResponse toSetResponse(WorkoutSet set) {
        return new WorkoutSetResponse(
                set.getId(),
                set.getExerciseName(),
                set.getReps(),
                set.getWeight(),
                set.getCreatedAt()
        );
    }
}
