package com.example.fitguide.data.TypeConverters

import androidx.room.TypeConverter
import com.example.fitguide.domain.model.MuscleGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MuscleGroupConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMuscleGroupList(muscleGroups: List<MuscleGroup>?): String {
        return gson.toJson(muscleGroups)
    }

    @TypeConverter
    fun toMuscleGroupList(data: String?): List<MuscleGroup>? {
        if (data.isNullOrEmpty()) return emptyList()
        val listType = object : TypeToken<List<MuscleGroup>>() {}.type
        return gson.fromJson(data, listType)
    }
}