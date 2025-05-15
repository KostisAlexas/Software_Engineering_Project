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
    val gender : String? = null,
    val age: Int? = null, // date of birth in milliseconds since epoch
    val height: Float? = null,   // height in cm
    val weight: Float? = null,   // weight in kg
    val coachId: Long? = null, // If the user has a coach.
    val isCoach: Boolean = false, // If the user is a coach.
) : Parcelable {
    // Methods for user-related operations (e.g., set goals, create workout, etc.)
    fun hasCoach() : Boolean{
        return coachId != null
    }

    fun isUserCoach() : Boolean{
        return isCoach
    }


}
