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


}
