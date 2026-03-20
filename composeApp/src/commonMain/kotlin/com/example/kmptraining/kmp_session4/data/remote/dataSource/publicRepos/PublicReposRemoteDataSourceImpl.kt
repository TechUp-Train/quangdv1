package com.example.kmptraining.kmp_session4.data.remote.dataSource.publicRepos

import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposRemoteDataSource
import com.example.kmptraining.kmp_session4.data.remote.dto.RepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubService

class PublicReposRemoteDataSourceImpl(
    private val githubService: GithubService,
) : PublicReposRemoteDataSource {
    override suspend fun fetchPublicRepos(): List<RepoDto> = githubService.getPublicRepos()
    override suspend fun searchRepos(
        query: String,
        sort: String?,
        order: String?,
        perPage: Int,
        page: Int
    ): SearchResultDto = githubService.searchRepos(query, sort, order, perPage, page)
}