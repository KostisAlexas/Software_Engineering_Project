package com.example.fitguide.domain.model

import java.util.Date


data class ScheduledWorkout(
    val athlete: User,
    val dateTime: Date,
    val workout: Workout,
    val exercises: List<ScheduledExercise>

){
}