package com.example.kmptraining.kmp_session4.domain.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.remote.dto.PublicRepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
import kotlinx.coroutines.flow.Flow

interface ExploreRepository {
    suspend fun getPublicRepos(): Flow<ResponseStatus<List<PublicRepoDto>>>
    suspend fun searchRepos(
        query: String,
        sort: String? = null,
        order: String? = null,
        perPage: Int = 30,
        page: Int = 1
    ): Flow<ResponseStatus<SearchResultDto>>
}