package com.example.kmptraining.kmp_session4.domain.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(id: Int): Flow<ResponseStatus<UserModel>>
    fun getAuthenticatedUser(): Flow<ResponseStatus<UserModel>>
}