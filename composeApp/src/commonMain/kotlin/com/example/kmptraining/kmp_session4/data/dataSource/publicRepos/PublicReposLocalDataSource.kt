package com.example.kmptraining.kmp_session4.data.dataSource.publicRepos

import com.example.kmptraining.kmp_session4.data.local.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

interface PublicReposLocalDataSource {
    suspend fun saveRepos(repos: List<RepoEntity>)
    fun observeRepos(): Flow<List<RepoEntity>?>
}
