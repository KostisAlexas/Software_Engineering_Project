package com.example.fitguide.data.dao

import androidx.room.*
import com.example.fitguide.data.entity.WorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity): Long

    @Query("SELECT * FROM workouts WHERE userId = :userId")
    fun getWorkoutsForUser(userId: Long): Flow<List<WorkoutEntity>>

    @Query("SELECT * FROM workouts WHERE workoutId = :id")
    suspend fun getWorkoutById(id: Long): WorkoutEntity?

    @Update
    suspend fun updateWorkout(workout: WorkoutEntity)

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)
}

