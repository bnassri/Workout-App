package com.example.workoutlogger.controller;

import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.service.WorkoutSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WorkoutHistoryPageController {

    private final WorkoutSessionService service;

    public WorkoutHistoryPageController(WorkoutSessionService service) {
        this.service = service;
    }

    @GetMapping("/workout-history")
    public String workoutHistory(Model model) {
        List<WorkoutSession> sessions = service.getAllSessions();

        // Convert to simple map with millis for JS
        List<Map<String, Object>> sessionsForView = sessions.stream().map(s -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", s.getId());
            map.put("startMillis", s.getStartTime() != null ? s.getStartTime().toEpochMilli() : 0L);
            map.put("endMillis", s.getEndTime() != null ? s.getEndTime().toEpochMilli() : 0L);
            map.put("totalSets", s.getSets() != null ? s.getSets().size() : 0);
            map.put("totalVolume", s.getSets() != null ?
                    s.getSets().stream().mapToDouble(set -> set.getReps() * set.getWeight()).sum() : 0);
            return map;
        }).toList();

        model.addAttribute("sessions", sessionsForView);
        return "workout-history";
    }
}

