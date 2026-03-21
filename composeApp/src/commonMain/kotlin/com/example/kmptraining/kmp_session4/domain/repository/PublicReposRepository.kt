package com.example.kmptraining.kmp_session4.domain.repository

import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import kotlinx.coroutines.flow.Flow

interface PublicReposRepository {
    fun getPublicRepos(): Flow<List<RepoModel>>

    fun getCachedRepos(): Flow<List<RepoModel>>

    fun searchRepos(
        query: String,
        sort: String? = null,
        order: String? = null,
        perPage: Int = 30,
        page: Int = 1
    ): Flow<List<RepoModel>>
}