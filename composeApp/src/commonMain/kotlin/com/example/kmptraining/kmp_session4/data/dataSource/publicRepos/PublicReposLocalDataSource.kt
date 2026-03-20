package com.example.kmptraining.kmp_session4.data.dataSource.publicRepos

import com.example.kmptraining.kmp_session4.data.local.entity.PublicRepoEntity
import kotlinx.coroutines.flow.Flow

interface PublicReposLocalDataSource {
    suspend fun saveRepos(repos: List<PublicRepoEntity>)
    fun observeRepos(): Flow<List<PublicRepoEntity>?>
}
