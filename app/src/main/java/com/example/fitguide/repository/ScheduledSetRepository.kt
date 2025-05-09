package com.example.fitguide.repository

import com.example.fitguide.domain.model.ScheduledSet
import kotlinx.coroutines.flow.Flow

interface ScheduledSetRepository {
    fun getSetsForExercise() : Flow<List<ScheduledSet>>
}