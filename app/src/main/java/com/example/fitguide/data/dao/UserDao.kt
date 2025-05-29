package com.example.fitguide.data.dao

import androidx.room.*
import com.example.fitguide.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("SELECT * FROM users WHERE userId = :id")
    suspend fun getUserById(id: Long): UserEntity?

    @Query("SELECT * FROM users WHERE coachId = :id")
    fun getUserAthletes(id: Long): Flow<List<UserEntity>>

    @Delete
    suspend fun deleteUser(user: UserEntity)
}