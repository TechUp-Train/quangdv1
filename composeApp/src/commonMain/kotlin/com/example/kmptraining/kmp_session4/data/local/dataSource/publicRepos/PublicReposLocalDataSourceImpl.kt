package com.example.kmptraining.kmp_session4.data.local.dataSource.publicRepos

import com.example.kmptraining.kmp_session4.data.local.dao.PublicRepoDao
import com.example.kmptraining.kmp_session4.data.local.entity.PublicRepoEntity
import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposLocalDataSource
import kotlinx.coroutines.flow.Flow

class PublicReposLocalDataSourceImpl(
    private val publicRepoDao: PublicRepoDao,
) : PublicReposLocalDataSource {
    override suspend fun saveRepos(repos: List<PublicRepoEntity>) {
        publicRepoDao.insertPublicRepos(repos)
    }

    override fun observeRepos(): Flow<List<PublicRepoEntity>?> = publicRepoDao.getPublicRepos()
}
