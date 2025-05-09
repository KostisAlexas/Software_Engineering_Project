package com.example.fitguide.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitguide.domain.model.ExerciseType

/**
 * Represents an exercise in the master list, including its type.
 */
@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val exerciseId: Long = 0,
    val name: String,
    val description: String?,
    val type: ExerciseType,    // determines which ScheduledSet fields apply
    val ownerId: Long? = null  // if null, it's a default exercise
)