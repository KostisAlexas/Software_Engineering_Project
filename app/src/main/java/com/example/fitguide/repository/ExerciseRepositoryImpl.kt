package com.example.fitguide.repository



import com.example.fitguide.data.dao.ExerciseDao
import com.example.fitguide.data.entity.ExerciseEntity
import com.example.fitguide.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ExerciseRepository {
    override suspend fun insertExercise(exercise: Exercise) : Long {
        return exerciseDao.insertExercise(exercise.toEntity())
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise.toEntity())
    }

    override fun getAllExercises(userId: Long): Flow<List<Exercise>> {
        return exerciseDao.getCustomExercisesForUser(userId).map { entities ->
            entities.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun getExerciseById(id: Long): Exercise? {
        return exerciseDao.getExerciseById(id)?.toDomain()
    }
}

fun ExerciseEntity.toDomain() : Exercise {
    return Exercise(
        id = exerciseId,
        name = name,
        description = description,
        type = type,
        primaryMuscleGroups = primaryMuscleGroups,
        secondaryMuscleGroups = secondaryMuscleGroups ?: emptyList(),
        ownerId = ownerId
    )
}

fun Exercise.toEntity() : ExerciseEntity {
    return ExerciseEntity(
        exerciseId = id ?: 0,
        name = name,
        description = description,
        type = type,
        primaryMuscleGroups = primaryMuscleGroups,
        secondaryMuscleGroups = secondaryMuscleGroups,
        ownerId = ownerId
    )
}

