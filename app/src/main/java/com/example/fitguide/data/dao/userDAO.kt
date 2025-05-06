package com.example.fitguide.data.dao

import androidx.room.*
import com.example.fitguide.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /**
     * Inserts a new user. Returns the generated userId.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity): Long

    /**
     * Fetches a user by username.
     */
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    /**
     * Authenticates a user by username and password hash.
     */
    @Query("SELECT * FROM users WHERE username = :username AND passwordHash = :passwordHash LIMIT 1")
    suspend fun authenticate(username: String, passwordHash: String): UserEntity?

    /**
     * Returns all users (for admin/coach views).
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    /**
     * Returns a user by their ID.
     */
    @Query("SELECT * FROM users WHERE userId = :id")
    suspend fun getUserById(id: Long): UserEntity?

    /**
     * Returns a user's coach.
     */
    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getCoachForUser(userId: Long): UserEntity?

    /**
     * Sets a user's coach.
     */
    @Query("UPDATE users SET coachId = :coachId WHERE userId = :userId")
    suspend fun setCoachForUser(userId: Long, coachId: Long)

    /**
     * Removes a user's coach.
     */
    @Query("UPDATE users SET coachId = NULL WHERE userId = :userId")
    suspend fun removeCoachForUser(userId: Long)

    /**
     * Returns a coach's athletes.
     */
    @Query("SELECT * FROM users WHERE coachId = :coachId")
    fun getAthletesForCoach(coachId: Long): Flow<List<UserEntity>>


    /**
     * Deletes a user.
     */
    @Delete
    suspend fun deleteUser(user: UserEntity)

    /**
     * Updates user info (e.g., password).
     */
    @Update
    suspend fun updateUser(user: UserEntity)
}
