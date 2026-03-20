package com.example.kmptraining.kmp_session4.data.dataSource.userRepos

import com.example.kmptraining.kmp_session4.data.remote.dto.UserRepoDto

interface UserReposRemoteDataSource {
    suspend fun fetchUserRepos(): List<UserRepoDto>
}