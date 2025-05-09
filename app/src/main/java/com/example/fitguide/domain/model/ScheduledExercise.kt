package com.example.fitguide.domain.model

data class ScheduledExercise(
    val id: Long? = null,
    val name: String,
    val description: String? = null,
    val ownerId: Long? = null,
    val exercise : Exercise,
    val sets: List<ScheduledSet>
) {


}