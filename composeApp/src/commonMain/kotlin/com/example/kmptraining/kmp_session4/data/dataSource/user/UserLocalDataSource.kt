package com.example.kmptraining.kmp_session4.data.dataSource.user

import com.example.kmptraining.kmp_session4.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveUser(user: UserEntity)
    fun observeUser(id: Int): Flow<UserEntity?>
}