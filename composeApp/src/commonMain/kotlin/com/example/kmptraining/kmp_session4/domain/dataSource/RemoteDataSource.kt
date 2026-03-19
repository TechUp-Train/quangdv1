package com.example.kmptraining.kmp_session4.domain.dataSource

import com.example.kmptraining.kmp_session4.data.remote.dto.UserDto
import com.example.kmptraining.kmp_session4.data.remote.dto.UserRepoDto

interface RemoteDataSource {
    suspend fun fetchUser(): UserDto
    suspend fun fetchUserRepos(): List<UserRepoDto>
}