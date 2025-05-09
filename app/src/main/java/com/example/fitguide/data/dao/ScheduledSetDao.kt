package com.example.fitguide.data.dao

import androidx.room.*
import com.example.fitguide.data.entity.ScheduledSetEntity


@Dao
interface ScheduledSetDao{

    @Query("SELECT * FROM scheduled_sets WHERE scheduledExerciseId = :scheduledExerciseId")
    suspend fun getScheduledSetsForExercise(scheduledExerciseId: Long): List<ScheduledSetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduledSet(scheduledSet: ScheduledSetEntity): Long
}