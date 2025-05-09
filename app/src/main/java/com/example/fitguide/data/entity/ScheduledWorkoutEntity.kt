package com.example.fitguide.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date


/**
 * Represents a scheduled workout instance (date & user-specific).
 */
@Entity(
    tableName = "scheduled_workouts",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutEntity::class,
            parentColumns = ["workoutId"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("workoutId"), Index("userId")]
)
data class ScheduledWorkoutEntity(
    @PrimaryKey(autoGenerate = true) val scheduledWorkoutId: Long = 0,
    val workoutId: Long,
    val userId: Long,           // user who scheduled this workout
    val dateTime: Long         // when the workout is planned
)