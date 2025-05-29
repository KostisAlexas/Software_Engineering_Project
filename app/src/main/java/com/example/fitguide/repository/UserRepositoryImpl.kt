package com.example.fitguide.repository


import com.example.fitguide.data.dao.UserDao
import com.example.fitguide.data.entity.UserEntity
import com.example.fitguide.domain.model.User
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user.toEntity())
    }

    override suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)?.toDomain()
    }

    override fun getUserAthletes(id: Long): Flow<List<User>> {
        val athletesFlow: Flow<List<UserEntity>> = userDao.getUserAthletes(id)
        return athletesFlow.map { entities ->
            entities.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun updateUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toEntity())
    }
}


fun UserEntity.toDomain(): User {
    return User(
        id = userId,
        username = username,
        password = passwordHash,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        dateOfBirth = dateOfBirth,
        height = height,
        weight = weight,
        coachId = coachId,
        isCoach = isCoach
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        userId = id ?: 0,
        username = username,
        passwordHash = password,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        dateOfBirth = dateOfBirth,
        height = height,
        weight = weight,
        coachId = coachId,
        isCoach = isCoach
    )
}
