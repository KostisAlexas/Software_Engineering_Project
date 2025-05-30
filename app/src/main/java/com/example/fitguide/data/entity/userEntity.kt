package com.example.fitguide.data.entity

import androidx.room.*
import java.time.Instant

/**
 * Represents an application user, e.g. coach or athlete.
 * User can only have one coach.
 */
@Entity(tableName = "users",
        foreignKeys = [
            ForeignKey(
                entity = UserEntity::class,
                parentColumns = ["userId"],
                childColumns = ["coachId"],
                onDelete = ForeignKey.SET_NULL)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val userId: Long = 0,
    val username: String,
    val passwordHash: String,    // hashed password for local auth
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender : String? = null,
    val dateOfBirth: Long? = null, // date of birth in milliseconds since epoch
    val height: Float? = null,   // height in cm
    val weight: Float? = null,   // weight in kg
    val coachId: Long? = null,   // if not null, this user has a coach
    val isCoach: Boolean = false
)