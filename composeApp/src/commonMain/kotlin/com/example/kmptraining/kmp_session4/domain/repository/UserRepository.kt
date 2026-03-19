package com.example.kmptraining.kmp_session4.domain.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.remote.dto.UserRepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): Flow<ResponseStatus<UserDto>>
    suspend fun getUserRepos(): Flow<ResponseStatus<List<UserRepoDto>>>
}
