package com.example.kmptraining.kmp_session4.data.local.dataSource.publicRepos

import com.example.kmptraining.kmp_session4.data.local.dao.RepoDao
import com.example.kmptraining.kmp_session4.data.local.entity.RepoEntity
import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposLocalDataSource
import kotlinx.coroutines.flow.Flow

class PublicReposLocalDataSourceImpl(
    private val repoDao: RepoDao,
) : PublicReposLocalDataSource {
    override suspend fun saveRepos(repos: List<RepoEntity>) {
        repoDao.insertRepos(repos)
    }

    override fun observeRepos(): Flow<List<RepoEntity>?> = repoDao.getReposByType("PUBLIC")
}
