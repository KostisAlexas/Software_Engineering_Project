package com.example.fitguide.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Represents a workout template created by the user.
 */
@Entity(
    tableName = "workouts",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["ownerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("ownerId")]
)
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val workoutId: Long = 0,
    val ownerId: Long,          // user who owns this workout
    val name: String,
    val description: String?
)