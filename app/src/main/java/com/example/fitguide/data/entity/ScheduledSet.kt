package com.example.fitguide.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Represents individual sets within a scheduled exercise.
 * Only fields relevant to the ExerciseType are used.
 */
@Entity(
    tableName = "scheduled_sets",
    foreignKeys = [
        ForeignKey(
            entity = ScheduledExerciseEntity::class,
            parentColumns = ["scheduledExerciseId"],
            childColumns = ["scheduledExerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("scheduledExerciseId")]
)
data class ScheduledSetEntity(
    @PrimaryKey(autoGenerate = true) val scheduledSetId: Long = 0,
    val scheduledExerciseId: Long,
    val reps: Int? = null,           // used for REPS, WEIGHTED
    val weightKg: Float? = null,     // used for WEIGHTED
    val durationSeconds: Int? = null, // used for DURATION, DISTANCE
    val distanceMeters: Int? = null   // used for DISTANCE
)