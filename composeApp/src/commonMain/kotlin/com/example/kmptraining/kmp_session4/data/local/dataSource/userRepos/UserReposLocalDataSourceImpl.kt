package com.example.kmptraining.kmp_session4.data.local.dataSource.userRepos

import com.example.kmptraining.kmp_session4.data.dataSource.userRepos.UserReposLocalDataSource
import com.example.kmptraining.kmp_session4.data.local.dao.UserRepoDao
import com.example.kmptraining.kmp_session4.data.local.entity.UserRepoEntity
import kotlinx.coroutines.flow.Flow

class UserReposLocalDataSourceImpl(
    private val userRepoDao: UserRepoDao,
) : UserReposLocalDataSource {

    override suspend fun saveRepos(repos: List<UserRepoEntity>) {
        userRepoDao.insertUserRepos(repos)
    }

    override fun observeRepos(): Flow<List<UserRepoEntity>> = userRepoDao.getUserRepos()
}