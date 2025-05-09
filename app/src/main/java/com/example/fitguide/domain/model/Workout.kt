package com.example.fitguide.domain.model

data class Workout(
    val id: Long? = null,
    val name: String,
    val description: String? = null,
    val ownerId: Long // The ID of the user who created this workout.
) {
    // Methods for workout-related actions.
}