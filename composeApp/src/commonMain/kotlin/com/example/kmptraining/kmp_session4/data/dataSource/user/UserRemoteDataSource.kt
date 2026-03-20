package com.example.kmptraining.kmp_session4.data.dataSource.user

import com.example.kmptraining.kmp_session4.data.remote.dto.UserDto

interface UserRemoteDataSource {
    suspend fun fetchUser(): UserDto
}