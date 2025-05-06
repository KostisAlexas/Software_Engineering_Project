package com.example.fitguide.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Links an exercise to a scheduled workout, with rest time and notes.
 */
@Entity(
    tableName = "scheduled_exercises",
    foreignKeys = [
        ForeignKey(
            entity = ScheduledWorkoutEntity::class,
            parentColumns = ["scheduledWorkoutId"],
            childColumns = ["scheduledWorkoutId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("scheduledWorkoutId"), Index("exerciseId")]
)
data class ScheduledExerciseEntity(
    @PrimaryKey(autoGenerate = true) val scheduledExerciseId: Long = 0,
    val scheduledWorkoutId: Long,
    val exerciseId: Long,
    val restTimeSeconds: Int,
    val notes: String?
)