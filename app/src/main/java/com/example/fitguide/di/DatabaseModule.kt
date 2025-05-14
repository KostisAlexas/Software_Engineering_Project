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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideApplicationScope() : CoroutineScope = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context,
        applicationScope: CoroutineScope
    ): AppDatabase {
        // Use AppDatabase.getDatabase() so that the callback is properly set
        return AppDatabase.getDatabase(appContext, applicationScope)
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
