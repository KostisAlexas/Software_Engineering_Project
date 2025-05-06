package com.example.fitguide.data.dao

import androidx.room.*
import com.example.fitguide.data.entity.ScheduledWorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduledWorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduledWorkout(scheduledWorkout: ScheduledWorkoutEntity): Long

    @Query("SELECT * FROM scheduled_workouts WHERE userId = :userId")
    fun getScheduledWorkoutsForUser(userId: Long): Flow<List<ScheduledWorkoutEntity>>

    @Query("SELECT * FROM scheduled_workouts WHERE scheduledWorkoutId = :id")
    suspend fun getScheduledWorkoutById(id: Long): ScheduledWorkoutEntity?

    @Update
    suspend fun updateScheduledWorkout(scheduledWorkout: ScheduledWorkoutEntity)

    @Delete
    suspend fun deleteScheduledWorkout(scheduledWorkout: ScheduledWorkoutEntity)
}
