package com.example.fitguide.repository

import com.example.fitguide.domain.model.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository{
    suspend fun getWorkoutsForUser(ownerId: Long): Flow<List<Workout>>
    suspend fun getWorkoutById(id: Long): Workout?
    suspend fun insertWorkout(workout: Workout): Long
    suspend fun updateWorkout(workout: Workout)
    suspend fun deleteWorkout(workout: Workout)
}