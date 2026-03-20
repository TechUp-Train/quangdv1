package com.example.kmptraining.kmp_session4.data.remote.service.github

import com.example.kmptraining.kmp_session4.data.remote.dto.RepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
import com.example.kmptraining.kmp_session4.data.remote.dto.UserDto

interface GithubService {
    suspend fun getUser(): UserDto
    suspend fun getPublicRepos(): List<RepoDto>
    suspend fun getUserRepos(): List<RepoDto>
    suspend fun searchRepos(
        query: String,
        sort: String? = null,
        order: String? = null,
        perPage: Int = 30,
        page: Int = 1
    ): SearchResultDto
}
