# Workout-App
A simple, web-based app for personal use to track workouts.  The goal is to create a locally hosted web application that allows a single user to log workouts and review historical data from both desktop and mobile devices without relying on third-party services or cloud infrastructure.

Requirements

Must Have

Log a workout consisting of:

    Date

    Exercise name

    Sets

    Repetitions

    Weight

    Optional notes

View a list of previously logged workouts

Run entirely locally

Accessible via desktop and mobile browser

Single-user system (no authentication)

Should Have

    Ability to edit or delete a logged workout

    Simple UI optimized for mobile use

    Persistent storage using a local database

Could Have

    Basic filtering by date or exercise

    Simple statistics (e.g., max weight per exercise)

Won’t Have (for MVP)

    Cloud sync

    User accounts or authentication

    Social or sharing features

Implementation

Project Setup

Technology Stack

    Java 17+

    Spring Boot (Web, Thymeleaf, Spring Data JPA)

    SQLite (local file-based DB)

    Maven for build

Initial Dependencies

    spring-boot-starter-web

    spring-boot-starter-thymeleaf

    spring-boot-starter-data-jpa

    sqlite-jdbc

Project Structure

com.example.workoutlogger
├── WorkoutLoggerApplication.java
├── controller
│   ├── WorkoutViewController.java
│   └── WorkoutSessionApiController.java
├── service
│   └── WorkoutSessionService.java
├── repository
│   ├── WorkoutSessionRepository.java
│   └── WorkoutSetRepository.java
├── domain
│   ├── WorkoutSession.java
│   └── WorkoutSet.java
├── dto
│   ├── CreateWorkoutSessionResponse.java
│   └── AddWorkoutSetRequest.java
└── resources
    ├── templates
    │   └── workouts.html
    └── application.yml

Entity Implementation Notes

    Use UUID as primary key

    IDs generated in application layer

    Map WorkoutSession → WorkoutSet as one-to-many

    Keep entities free of UI concerns

First Runnable Milestone (MVP Core)

Goal: Start a workout session and add a set via REST API

Steps:

    Create Spring Boot application

    Configure SQLite datasource

    Implement WorkoutSession and WorkoutSet entities

    Implement repositories using Spring Data JPA

    Implement REST endpoints:

        POST /api/workout-sessions/start

        POST /api/workout-sessions/{id}/sets

    Verify data persistence via SQLite file

At this milestone, the system is usable via HTTP clients (browser or curl).

Second Milestone (User Interface)

    Add Thymeleaf page for:

        Viewing past workout sessions

Add JavaScript-powered live session page:

    Start session

    Add sets

    Rest timer

    Live volume calculation

Milestones

    Foundation – Spring Boot app runs, SQLite configured

    Session API – Workout session lifecycle implemented

    Persistence – Sets stored and retrievable

    Live Session UI – JavaScript-based workout logging

    History View – Server-rendered workout browsing

Gathering Results

    Verify workouts persist across application restarts

    Measure UI responsiveness during live session logging

    Validate that requirements under "Must Have" are satisfied

    Confirm architecture supports future client-rendered frontend without refactoring
