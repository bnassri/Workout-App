package com.example.workoutlogger.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Response DTO for WorkoutSession API endpoints.
 * 
 * This DTO prevents circular reference issues when serializing to JSON
 * and provides a clean API contract independent of the database schema.
 */
public class WorkoutSessionResponse {
    
    private UUID id;
    private Instant startTime;
    private Instant endTime;
    private String status;
    private List<WorkoutSetResponse> sets;
    private UUID templateId;
    private String templateName;
    
    // Default constructor for Jackson
    public WorkoutSessionResponse() {
    }
    
    // Constructor for basic session info (no template)
    public WorkoutSessionResponse(UUID id, Instant startTime, Instant endTime, String status, List<WorkoutSetResponse> sets) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.sets = sets;
    }
    
    // Full constructor with template info
    public WorkoutSessionResponse(UUID id, Instant startTime, Instant endTime, String status, 
                                  List<WorkoutSetResponse> sets, UUID templateId, String templateName) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.sets = sets;
        this.templateId = templateId;
        this.templateName = templateName;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public Instant getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }
    
    public Instant getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<WorkoutSetResponse> getSets() {
        return sets;
    }
    
    public void setSets(List<WorkoutSetResponse> sets) {
        this.sets = sets;
    }
    
    public UUID getTemplateId() {
        return templateId;
    }
    
    public void setTemplateId(UUID templateId) {
        this.templateId = templateId;
    }
    
    public String getTemplateName() {
        return templateName;
    }
    
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
