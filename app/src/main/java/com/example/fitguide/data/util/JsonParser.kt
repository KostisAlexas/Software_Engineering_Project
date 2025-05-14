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

fun parseDefaultExercises(context: Context): List<ExerciseJson> {
    val inputStream = context.assets.open("default_exercises.json")
    val reader = InputStreamReader(inputStream)
    val type = object : TypeToken<List<ExerciseJson>>() {}.type
    return Gson().fromJson(reader, type)
}