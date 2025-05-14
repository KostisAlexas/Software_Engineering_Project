package com.example.fitguide.domain.model

import com.example.fitguide.domain.model.Exercise
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class `User`(
    val id: Long? = null,
    val username: String,
    val password: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val coachId: Long? = null // If the user has a coach.
) : Parcelable {
    // Methods for user-related operations (e.g., set goals, create workout, etc.)
    fun hasCoach() : Boolean{
        return coachId != null
    }


}
