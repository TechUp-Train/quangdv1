package com.example.kmptraining.kmp_session4.domain.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import kotlinx.coroutines.flow.Flow

interface UserReposRepository {
    fun getUserRepos(): Flow<ResponseStatus<List<RepoModel>>>
}