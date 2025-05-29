package com.example.fitguide.domain.model

import java.util.Date
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduledWorkout(
    val userId: Long, // The user for whom this workout is scheduled.
    val dateTime: Date,
    val workout: Workout

) : Parcelable {
}