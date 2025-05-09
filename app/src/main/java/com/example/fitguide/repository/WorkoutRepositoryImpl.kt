package com.example.fitguide.repository

import com.example.fitguide.data.dao.WorkoutDao
import com.example.fitguide.data.entity.WorkoutEntity
import com.example.fitguide.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val workoutDao: WorkoutDao
) : WorkoutRepository {
    override suspend fun getWorkoutsForUser(ownerId: Long): Flow<List<Workout>> {
        return workoutDao.getWorkoutsForUser(ownerId).map { entities ->
            entities.map { entity ->
                Workout(
                    id = entity.workoutId,
                    name = entity.name,
                    description = entity.description,
                    ownerId = entity.ownerId
                )
            }
        }
    }

    override suspend fun getWorkoutById(id: Long): Workout? {
        return workoutDao.getWorkoutById(id)?.let { entity ->
            Workout(
                id = entity.workoutId,
                name = entity.name,
                description = entity.description,
                ownerId = entity.ownerId
            )
        }
    }

    override suspend fun insertWorkout(workout: Workout): Long {
        val entity = WorkoutEntity(
            name = workout.name,
            description = workout.description,
            ownerId = workout.ownerId
        )
        return workoutDao.insertWorkout(entity)
    }

    override suspend fun updateWorkout(workout: Workout) {
        val entity = WorkoutEntity(
            workoutId = workout.id ?: throw IllegalArgumentException("Workout ID cannot be null for update"),
            name = workout.name,
            description = workout.description,
            ownerId = workout.ownerId
            )
        workoutDao.updateWorkout(entity)
    }

    override suspend fun deleteWorkout(workout: Workout) {
        val entity = WorkoutEntity(
            workoutId = workout.id ?: throw IllegalArgumentException("Workout ID cannot be null for deletion"),
            name = workout.name,
            description = workout.description,
            ownerId = workout.ownerId
            )
        workoutDao.deleteWorkout(entity)
    }

}