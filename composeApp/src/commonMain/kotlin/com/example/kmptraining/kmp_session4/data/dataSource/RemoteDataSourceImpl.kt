package com.example.kmptraining.kmp_session4.data.dataSource

import com.example.kmptraining.kmp_session4.data.remote.dto.UserDto
import com.example.kmptraining.kmp_session4.data.remote.dto.UserRepoDto
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubService
import com.example.kmptraining.kmp_session4.domain.dataSource.RemoteDataSource

class RemoteDataSourceImpl(
    private val githubService: GithubService,
) : RemoteDataSource {
    override suspend fun fetchUser() = githubService.getUser()

    override suspend fun fetchUserRepos(): List<UserRepoDto> = githubService.getUserRepos()
}