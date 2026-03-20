package com.example.kmptraining.kmp_session4.data.dataSource.userRepos

import com.example.kmptraining.kmp_session4.data.local.entity.UserRepoEntity
import kotlinx.coroutines.flow.Flow

interface UserReposLocalDataSource {
    suspend fun saveRepos(repos: List<UserRepoEntity>)
    fun observeRepos(): Flow<List<UserRepoEntity>>
}