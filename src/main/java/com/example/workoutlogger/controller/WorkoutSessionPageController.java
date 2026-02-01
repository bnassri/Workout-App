package com.example.workoutlogger.controller;

import com.example.workoutlogger.dto.AddWorkoutSetRequest;
import com.example.workoutlogger.repository.WorkoutSessionRepository;
import com.example.workoutlogger.repository.WorkoutSetRepository;
import com.example.workoutlogger.domain.WorkoutSession;
import com.example.workoutlogger.domain.WorkoutSet;
import com.example.workoutlogger.domain.WorkoutTemplate;
import com.example.workoutlogger.domain.WorkoutTemplateExercise;
import com.example.workoutlogger.service.WorkoutSessionService;
import com.example.workoutlogger.service.WorkoutTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/workout-sessions")
public class WorkoutSessionPageController {

    private final WorkoutSessionService workoutSessionService;
    private final WorkoutTemplateService templateService;

    public WorkoutSessionPageController(
            WorkoutSessionService sessionService,
            WorkoutTemplateService templateService
    ) {
        this.workoutSessionService = sessionService;
        this.templateService = templateService;
    }


    /**
     * Render the start workout page (optional: show template selection)
     */
    @GetMapping("/start")
    public String startWorkoutPage(Model model) {
        List<WorkoutTemplate> templates = templateService.getAllTemplates();
        model.addAttribute("templates", templates);
        return "start-workout"; // a Thymeleaf page with a form to select template or start ad-hoc
    }

    /**
     * Start a workout session
     */
    @PostMapping("/start")
    public String startWorkout(
            @RequestParam(required = false) UUID templateId
    ) {
        WorkoutSession session;

        if (templateId != null) {
            WorkoutTemplate template = templateService.getTemplateById(templateId);
            session = workoutSessionService.startSession(template);
        } else {
            session = workoutSessionService
                    .startSession();
        }

        return "redirect:/workout-sessions/" + session.getId();
    }


    /**
     * Renders the workout session page
     */

    @GetMapping("/{id}")
    public String workoutSessionPage(
            @PathVariable UUID id,
            Model model
    ) {
        WorkoutSession session = workoutSessionService.getSession(id);

        // If session has a template but no sets yet, pre-populate one blank set per exercise
        if (session.getTemplate() != null && session.getSets().isEmpty()) {
            for (WorkoutTemplateExercise templateExercise : session.getTemplate().getExercises()) {
                WorkoutSet blankSet = new WorkoutSet(
                        UUID.randomUUID(),
                        session,
                        templateExercise.getExerciseName(),
                        0, // default reps
                        0, // default weight
                        Instant.now()
                );
                session.getSets().add(blankSet);
                // Save directly via repository
                workoutSessionService.saveSet(blankSet);
            }
        }


        // Group sets by exercise name
        Map<String, List<WorkoutSet>> setsByExercise = new LinkedHashMap<>();
        for (WorkoutSet set : session.getSets()) {
            setsByExercise.computeIfAbsent(set.getExerciseName(), k -> new ArrayList<>())
                    .add(set);
        }
        if (setsByExercise.isEmpty()) {
            model.addAttribute("setsByExercise", Collections.emptyMap());
        }

        model.addAttribute("session", session);
        model.addAttribute("sessionId", session.getId());
        model.addAttribute("startTimeMillis", session.getStartTime().toEpochMilli());
        model.addAttribute("setsByExercise", setsByExercise);

        return "workout-session";
    }


    @PostMapping("/{id}/sets/{setId}")
    public String saveSet(
            @PathVariable UUID id,
            @PathVariable UUID setId,
            @RequestParam int reps,
            @RequestParam double weight
    ) {
        WorkoutSession session = workoutSessionService.getSession(id);

        WorkoutSet set = session.getSets().stream()
                .filter(s -> s.getId().equals(setId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Set not found"));

        set.setReps(reps);
        set.setWeight(weight);
        workoutSessionService.saveSet(set);

        return "redirect:/workout-sessions/" + id;
    }
    @PostMapping("/{id}/sets/add-new")
    public String addSet(
            @PathVariable UUID id,
            @RequestParam("exerciseName") String exerciseName
    ) {
        AddWorkoutSetRequest request = new AddWorkoutSetRequest();
        request.setExerciseName(exerciseName);
        request.setReps(0);
        request.setWeight(0);

        workoutSessionService.addSet(id, request);

        return "redirect:/workout-sessions/" + id;
    }



}
