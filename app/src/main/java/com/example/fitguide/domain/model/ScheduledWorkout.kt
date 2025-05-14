package com.example.fitguide.domain.model

import java.util.Date
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduledWorkout(
    val athlete: User,
    val dateTime: Date,
    val workout: Workout,
    val exercises: List<ScheduledExercise>

) : Parcelable {
}