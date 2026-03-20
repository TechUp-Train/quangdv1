package com.example.kmptraining.kmp_session4.domain.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import kotlinx.coroutines.flow.Flow

interface PublicReposRepository {
    fun getPublicRepos(): Flow<ResponseStatus<List<RepoModel>>>

    suspend fun searchRepos(
        query: String,
        sort: String? = null,
        order: String? = null,
        perPage: Int = 30,
        page: Int = 1
    ): Flow<ResponseStatus<SearchResultDto>>
}