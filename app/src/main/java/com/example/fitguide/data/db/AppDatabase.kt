package com.example.fitguide.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitguide.data.dao.*
import com.example.fitguide.data.entity.*

@Database(entities = [
    WorkoutEntity::class,
    ExerciseEntity::class,
    UserEntity::class,
    ScheduledWorkoutEntity::class,
    ScheduledExerciseEntity::class,
    ScheduledSetEntity::class, ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun scheduledWorkoutDao(): ScheduledWorkoutDao
    abstract fun scheduledExerciseDao(): ScheduledExerciseDao
    abstract fun scheduledSetDao(): ScheduledSetDao

}