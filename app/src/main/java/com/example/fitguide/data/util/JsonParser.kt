package com.example.fitguide.data.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

data class ExerciseJson(
    val name: String,
    val description: String?,
    val type: String,
    val primaryMuscleGroups: List<String>,
    val secondaryMuscleGroups: List<String>?
)

data class UserJson(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val age: Long,
    val height: Float,
    val weight: Float,
    val coachId: Long?,
    val isCoach: Boolean
)

fun parseDefaultExercises(context: Context): List<ExerciseJson> {
    val inputStream = context.assets.open("default_exercises.json")
    val reader = InputStreamReader(inputStream)
    val type = object : TypeToken<List<ExerciseJson>>() {}.type
    return Gson().fromJson(reader, type)
}

fun parseUsers(context: Context): List<UserJson> {
    val inputStream = context.assets.open("test_case_users.json")
    val reader = InputStreamReader(inputStream)
    val type = object : TypeToken<List<UserJson>>() {}.type
    return Gson().fromJson(reader, type)
}