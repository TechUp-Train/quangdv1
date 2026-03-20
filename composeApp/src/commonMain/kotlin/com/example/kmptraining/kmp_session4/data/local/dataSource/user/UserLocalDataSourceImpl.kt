package com.example.kmptraining.kmp_session4.data.local.dataSource.user

import com.example.kmptraining.kmp_session4.data.dataSource.user.UserLocalDataSource
import com.example.kmptraining.kmp_session4.data.local.dao.UserDao
import com.example.kmptraining.kmp_session4.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserLocalDataSourceImpl(
    private val userDao: UserDao,
) : UserLocalDataSource {
    override suspend fun saveUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override fun observeUser(id: Int): Flow<UserEntity?> = userDao.getUserById(id)

    override fun observeUsers(): Flow<List<UserEntity>> = userDao.getUsers()
}