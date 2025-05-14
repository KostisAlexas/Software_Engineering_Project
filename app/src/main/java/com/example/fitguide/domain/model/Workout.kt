package com.example.fitguide.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Workout(
    val id: Long? = null,
    val name: String,
    val description: String? = null,
    val ownerId: Long // The ID of the user who created this workout.
) : Parcelable {
    // Methods for workout-related actions.
}