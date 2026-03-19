package com.example.kmptraining.kmp_session4.domain.dataSource

import com.example.kmptraining.kmp_session4.data.local.entity.UserEntity
import com.example.kmptraining.kmp_session4.data.local.entity.UserRepoEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveUser(user: UserEntity)
    fun observeUser(id: Int): Flow<UserEntity?>
    suspend fun saveRepos(repos: List<UserRepoEntity>)
    fun observeRepos(): Flow<List<UserRepoEntity>>
}