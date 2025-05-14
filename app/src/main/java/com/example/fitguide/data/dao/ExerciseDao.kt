package com.example.fitguide.data.dao

import androidx.room.*
import com.example.fitguide.data.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity): Long

    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)


    @Query("SELECT * FROM exercises WHERE ownerId = :ownerId OR ownerId IS NULL")
    fun getCustomExercisesForUser(ownerId: Long): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE ownerId IS NULL")
    fun getDefaultExercises(): Flow<List<ExerciseEntity>>


    @Query("SELECT * FROM exercises WHERE exerciseId = :id")
    suspend fun getExerciseById(id: Long): ExerciseEntity?

}