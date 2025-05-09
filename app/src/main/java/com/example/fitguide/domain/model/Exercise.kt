package com.example.fitguide.domain.model

data class Exercise(
    val id: Long? = null,
    val name: String,
    val description: String? = null,
    val type: ExerciseType,
    val ownerId: Long? = null
) {

}