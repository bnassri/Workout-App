package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.dto.WorkoutSummaryView;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.util.UUID;

@Controller
public class WorkoutSummaryPageController {

    private final WorkoutSessionService service;

    public WorkoutSummaryPageController(WorkoutSessionService service) {
        this.service = service;
    }

    @GetMapping("/workout-sessions/{id}/summary")
    public String workoutSummary(@PathVariable UUID id, Model model) {

        WorkoutSummaryView summary = service.getWorkoutSummaryView(id);

        model.addAttribute("summary", summary);

        return "workout-summary";
    }


}
