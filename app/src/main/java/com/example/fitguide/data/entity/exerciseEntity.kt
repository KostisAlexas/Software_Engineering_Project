package com.example.fitguide.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines the types of exercises and drives which set fields apply.
 */
enum class ExerciseType {
    REPS,         // fields: reps, optional weightKg
    DURATION,     // fields: durationSeconds
    WEIGHTED,     // fields: reps, weightKg
    DISTANCE      // fields: durationSeconds (e.g., for running)
}

/**
 * Represents an exercise in the master list, including its type.
 */
@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val exerciseId: Long = 0,
    val name: String,
    val description: String?,
    val type: ExerciseType    // determines which ScheduledSet fields apply
)