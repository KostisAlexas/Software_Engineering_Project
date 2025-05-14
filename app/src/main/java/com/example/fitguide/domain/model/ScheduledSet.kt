package com.example.fitguide.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ScheduledSet(
    open val id: Long? = null,
    open val exerciseType: ExerciseType
) : Parcelable {
    data class WeightedSet(
        override val id: Long? = null,
        override val exerciseType: ExerciseType = ExerciseType.WEIGHTED,
        val reps: Int,
        val weightKg: Float
    ) : ScheduledSet(id, exerciseType)

    data class RepsSet(
        override val id: Long? = null,
        override val exerciseType: ExerciseType = ExerciseType.REPS,
        val reps: Int
    ) : ScheduledSet(id, exerciseType)

    data class DurationSet(
        override val id: Long? = null,
        override val exerciseType: ExerciseType = ExerciseType.DURATION,
        val durationSeconds: Int
    ) : ScheduledSet(id, exerciseType)

    data class DistanceSet(
        override val id: Long? = null,
        override val exerciseType: ExerciseType = ExerciseType.DISTANCE,
        val distanceMeters: Int
    ) : ScheduledSet(id, exerciseType)

}