package com.example.fitguide.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduledExercise(
    val id: Long? = null,
    val description: String? = null,
    val ownerId: Long? = null,
    val exercise : Exercise,
    val sets: List<ScheduledSet>
) : Parcelable {

    fun calculateVolume(): Int {
        return sets.sumOf { it.calculateVolume() }
    }

    fun calculateTotalReps(): Int {
        if (exercise.type == ExerciseType.REPS || exercise.type == ExerciseType.WEIGHTED) {
            return sets.sumOf { it.calculateReps() }
        } else {
            return sets.size // For time-based exercises, each set counts as one rep
        }
    }





}
