package com.example.fitguide.data.dao

import androidx.room.*
import com.example.fitguide.data.entity.ScheduledExerciseEntity

@Dao
interface ScheduledExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduledExercise(scheduledExercise: ScheduledExerciseEntity): Long

}