package com.example.kmptraining.kmp_session4.data.remote.dataSource.userRepos

import com.example.kmptraining.kmp_session4.data.dataSource.userRepos.UserReposRemoteDataSource
import com.example.kmptraining.kmp_session4.data.remote.dto.RepoDto
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubService

class UserReposRemoteDataSourceImpl(
    private val githubService: GithubService,
) : UserReposRemoteDataSource {
    override suspend fun fetchUserRepos(): List<RepoDto> = githubService.getUserRepos()
}