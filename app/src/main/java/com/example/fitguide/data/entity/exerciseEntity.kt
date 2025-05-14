package com.example.fitguide.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.fitguide.domain.model.ExerciseType
import com.example.fitguide.domain.model.MuscleGroup


/**
 * Represents an exercise in the master list, including its type.
 */
@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val exerciseId: Long = 0,
    val name: String,
    val description: String?,
    val type: ExerciseType,    // determines which ScheduledSet fields apply
    val primaryMuscleGroups: List<MuscleGroup> = emptyList(), // Contributes one set to volume
    val secondaryMuscleGroups: List<MuscleGroup>? = emptyList(), // Contributes half a set to volume
    val ownerId: Long? = null  // if null, it's a default exercise
){


}