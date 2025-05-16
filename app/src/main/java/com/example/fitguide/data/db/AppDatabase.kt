package com.example.fitguide.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitguide.data.TypeConverters.*
import com.example.fitguide.data.dao.*
import com.example.fitguide.data.entity.*
import com.example.fitguide.data.util.*
import com.example.fitguide.domain.model.ExerciseType
import com.example.fitguide.domain.model.MuscleGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [
    WorkoutEntity::class,
    ExerciseEntity::class,
    UserEntity::class,
    ScheduledWorkoutEntity::class,
    ScheduledExerciseEntity::class,
    ScheduledSetEntity::class, ],
    version = 3
)

@TypeConverters(
    MuscleGroupConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun scheduledWorkoutDao(): ScheduledWorkoutDao
    abstract fun scheduledExerciseDao(): ScheduledExerciseDao
    abstract fun scheduledSetDao(): ScheduledSetDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitguide_database"
                )
                    .addCallback(AppDatabaseCallback(scope, context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    public class AppDatabaseCallback(
        private val scope: CoroutineScope,
        private val context: Context
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateExercises(database.exerciseDao(), context)
                    populateUsers(database.userDao(), context)
                }
            }
        }
    }
}

suspend fun populateExercises(exerciseDao: ExerciseDao, context: Context) {
    val defaultExercises = parseDefaultExercises(context)
    val exerciseEntities = defaultExercises.map { exerciseJson ->
        ExerciseEntity(
            name = exerciseJson.name,
            description = exerciseJson.description,
            type = ExerciseType.valueOf(exerciseJson.type),
            primaryMuscleGroups = exerciseJson.primaryMuscleGroups.map { MuscleGroup.valueOf(it) },
            secondaryMuscleGroups = exerciseJson.secondaryMuscleGroups?.map { MuscleGroup.valueOf(it) } ?: emptyList(),
            ownerId = null
        )
    }
    exerciseEntities.forEach { exerciseEntity ->
        exerciseDao.insertExercise(exerciseEntity)
    }
}

suspend fun populateUsers(userDao: UserDao, context: Context) {
    val testUsers = parseUsers(context)
    val userEntities = testUsers.map { userJson ->
        UserEntity(
            userId = userJson.id,
            username = userJson.username,
            passwordHash = userJson.password,
            firstName = userJson.firstName,
            lastName = userJson.lastName,
            email = userJson.email,
            gender = userJson.gender,
            dateOfBirth = userJson.age,
            height = userJson.height,
            weight = userJson.weight,
            coachId = userJson.coachId,
            isCoach = userJson.isCoach
        )
    }
    userEntities.forEach { userEntity ->
        userDao.insertUser(userEntity)
    }
}
