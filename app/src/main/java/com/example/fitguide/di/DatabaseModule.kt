package com.example.fitguide.di

import android.content.Context
import androidx.room.Room
import com.example.fitguide.data.dao.*
import com.example.fitguide.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database" // You can change the database name here if you want
        ).build()
    }

    @Provides
    fun provideWorkoutDao(appDatabase: AppDatabase): WorkoutDao {
        return appDatabase.workoutDao()
    }

    @Provides
    fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
        return appDatabase.exerciseDao()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideScheduledWorkoutDao(appDatabase: AppDatabase): ScheduledWorkoutDao {
        return appDatabase.scheduledWorkoutDao()
    }

    @Provides
    fun provideScheduledExerciseDao(appDatabase: AppDatabase): ScheduledExerciseDao {
        return appDatabase.scheduledExerciseDao()
    }

    @Provides
    fun provideScheduledSetDao(appDatabase: AppDatabase): ScheduledSetDao {
        return appDatabase.scheduledSetDao()
    }

}