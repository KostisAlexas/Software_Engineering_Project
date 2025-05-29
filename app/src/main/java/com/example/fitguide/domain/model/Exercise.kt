package com.example.fitguide.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(
    val id: Long? = null,
    val name: String,
    val description: String?,
    val type: ExerciseType,
    val primaryMuscleGroups: List<MuscleGroup>,
    val secondaryMuscleGroups: List<MuscleGroup>,
    val ownerId: Long? = null
) : Parcelable

