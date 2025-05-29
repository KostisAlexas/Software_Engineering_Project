package com.example.fitguide.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ScheduledSet(
    open val id: Long? = null,
    open val exerciseType: ExerciseType,
    val RPE: Int? = null,

) : Parcelable {
    abstract fun calculateVolume(): Int
    abstract fun calculateReps(): Int

    data class WeightedSet(
        override val id: Long? = null,
        override val exerciseType: ExerciseType = ExerciseType.WEIGHTED,
        val reps: Int,
        val weightKg: Float
    ) : ScheduledSet(id, exerciseType)
    {
        override fun calculateVolume(): Int {
            return (reps * weightKg).toInt()
        }

        override fun calculateReps(): Int {
            return reps
        }
    }
    data class RepsSet(
        override val id: Long? = null,
        override val exerciseType: ExerciseType = ExerciseType.REPS,
        val reps: Int
    ) : ScheduledSet(id, exerciseType)
    {
        override fun calculateVolume(): Int {
            return reps
        }

        override fun calculateReps(): Int {
            return reps
        }
    }

    data class DurationSet(
        override val id: Long? = null,
        override val exerciseType: ExerciseType = ExerciseType.DURATION,
        val durationSeconds: Int
    ) : ScheduledSet(id, exerciseType)
    {
        override fun calculateVolume(): Int {
            return durationSeconds
        }

        override fun calculateReps(): Int {
            return 0 // Duration sets don't have reps
        }
    }

    data class DistanceSet(
        override val id: Long? = null,
        override val exerciseType: ExerciseType = ExerciseType.DISTANCE,
        val distanceMeters: Int,
        val durationSeconds: Int
    ) : ScheduledSet(id, exerciseType)
    {
        override fun calculateVolume(): Int {
            return distanceMeters + durationSeconds
        }

        override fun calculateReps(): Int {
            return 0 // Distance sets don't have reps
        }
    }

}