package com.example.kmptraining.kmp_session4.data.dataSource.publicRepos

import com.example.kmptraining.kmp_session4.data.remote.dto.PublicRepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto

interface PublicReposRemoteDataSource {
    suspend fun fetchPublicRepos(): List<PublicRepoDto>
    suspend fun searchRepos(
        query: String,
        sort: String? = null,
        order: String? = null,
        perPage: Int = 30,
        page: Int = 1
    ): SearchResultDto
}