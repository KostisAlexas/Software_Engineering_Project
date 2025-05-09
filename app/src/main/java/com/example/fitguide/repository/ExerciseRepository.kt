package com.example.fitguide.repository

import com.example.fitguide.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun insertExercise(exercise: Exercise): Long
    suspend fun deleteExercise(exercise: Exercise)
    suspend fun getExerciseById(id: Long): Exercise?
    fun getAllExercises(userId: Long): Flow<List<Exercise>>


}