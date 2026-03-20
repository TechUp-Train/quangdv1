package com.example.kmptraining.kmp_session4.data.remote.dataSource.user

import com.example.kmptraining.kmp_session4.data.dataSource.user.UserRemoteDataSource
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubService

class UserRemoteDataSourceImpl(
    private val githubService: GithubService,
) : UserRemoteDataSource {
    override suspend fun fetchUser() = githubService.getUser()
}