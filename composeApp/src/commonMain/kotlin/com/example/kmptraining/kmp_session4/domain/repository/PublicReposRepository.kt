package com.example.kmptraining.kmp_session4.domain.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.remote.dto.PublicRepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
import com.example.kmptraining.kmp_session4.domain.model.PublicRepoModel
import kotlinx.coroutines.flow.Flow

interface PublicReposRepository {
    fun getPublicRepos(): Flow<ResponseStatus<List<PublicRepoModel>>>

    suspend fun searchRepos(
        query: String,
        sort: String? = null,
        order: String? = null,
        perPage: Int = 30,
        page: Int = 1
    ): Flow<ResponseStatus<SearchResultDto>>
}