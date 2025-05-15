package com.example.fitguide.repository

import com.example.fitguide.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User): Long
    suspend fun getUserById(id: Long): User?
    suspend fun getUserCoach(id: Long): User?
    fun getUserAthletes(id: Long): Flow<List<User>>
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}