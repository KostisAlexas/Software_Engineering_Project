package com.example.fitguide.domain.model

// Defines corresponding set attributes for an exercise.
enum class ExerciseType {
    REPS,         // fields: reps, optional weightKg
    DURATION,     // fields: durationSeconds
    WEIGHTED,     // fields: reps, weightKg
    DISTANCE      // fields: durationSeconds (e.g., for running)
}