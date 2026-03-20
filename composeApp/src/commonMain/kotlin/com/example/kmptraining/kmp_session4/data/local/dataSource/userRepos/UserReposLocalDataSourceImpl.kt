package com.example.kmptraining.kmp_session4.data.local.dataSource.userRepos

import com.example.kmptraining.kmp_session4.data.dataSource.userRepos.UserReposLocalDataSource
import com.example.kmptraining.kmp_session4.data.local.dao.RepoDao
import com.example.kmptraining.kmp_session4.data.local.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

class UserReposLocalDataSourceImpl(
    private val repoDao: RepoDao,
) : UserReposLocalDataSource {

    override suspend fun saveRepos(repos: List<RepoEntity>) {
        repoDao.insertRepos(repos)
    }

    override fun observeRepos(): Flow<List<RepoEntity>> = repoDao.getReposByType("OWNED")
}