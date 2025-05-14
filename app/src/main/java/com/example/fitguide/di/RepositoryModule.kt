package com.example.fitguide.di

import dagger.Module
import dagger.Binds
import com.example.fitguide.repository.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindWorkoutRepository(
        workoutRepositoryImpl: WorkoutRepositoryImpl
    ): WorkoutRepository

    @Binds
    abstract fun bindExerciseRepository(
        exerciseRepositoryImpl: ExerciseRepositoryImpl
    ): ExerciseRepository

//    @Binds
//    abstract fun bindUserRepository(
//        userRepositoryImpl: UserRepositoryImpl
//    ): UserRepository
//
//    @Binds
//    abstract fun bindScheduledWorkoutRepository(
//        scheduledWorkoutRepositoryImpl: ScheduledWorkoutRepositoryImpl
//    ): ScheduledWorkoutRepository
//
//    @Binds
//    abstract fun bindScheduledExerciseRepository(
//        scheduledExerciseRepositoryImpl: ScheduledExerciseRepositoryImpl
//    ): ScheduledExerciseRepository
//
//    @Binds
//    abstract fun bindScheduledSetRepository(
//        scheduledSetRepositoryImpl: ScheduledSetRepositoryImpl
//    ): ScheduledSetRepository
}