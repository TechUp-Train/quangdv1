package com.example.kmptraining.kmp_session4.data.dataSource

import com.example.kmptraining.kmp_session4.data.local.dao.UserDao
import com.example.kmptraining.kmp_session4.data.local.dao.UserRepoDao
import com.example.kmptraining.kmp_session4.data.local.entity.UserEntity
import com.example.kmptraining.kmp_session4.data.local.entity.UserRepoEntity
import com.example.kmptraining.kmp_session4.domain.dataSource.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val userDao: UserDao,
    private val userRepoDao: UserRepoDao,
) : LocalDataSource {
    override suspend fun saveUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override fun observeUser(id: Int): Flow<UserEntity?> = userDao.getUserById(id)

    override suspend fun saveRepos(repos: List<UserRepoEntity>) {
        userRepoDao.insertUserRepos(repos)
    }

    override fun observeRepos(): Flow<List<UserRepoEntity>> = userRepoDao.getUserRepos()
}