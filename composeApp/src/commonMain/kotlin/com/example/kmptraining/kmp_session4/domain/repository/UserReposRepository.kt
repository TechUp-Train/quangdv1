package com.example.kmptraining.kmp_session4.domain.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.UserRepoModel
import kotlinx.coroutines.flow.Flow

interface UserReposRepository {
    fun getUserRepos(): Flow<ResponseStatus<List<UserRepoModel>>>
}