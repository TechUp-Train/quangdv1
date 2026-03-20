package com.example.kmptraining.kmp_session4.data.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.mapper.toEntity
import com.example.kmptraining.kmp_session4.data.mapper.toModel
import com.example.kmptraining.kmp_session4.data.dataSource.user.UserLocalDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.user.UserRemoteDataSource
import com.example.kmptraining.kmp_session4.domain.model.UserModel
import com.example.kmptraining.kmp_session4.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.io.IOException

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {
    override fun getUser(id: Int): Flow<ResponseStatus<UserModel>> {
        return channelFlow {
            userLocalDataSource.observeUser(id).collect { entity ->
                entity?.let { localData ->
                    send(ResponseStatus.Success(localData.toModel()))
                } ?: run {
                    send(ResponseStatus.Loading)
                }
            }

            launch {
                try {
                    val dto = userRemoteDataSource.fetchUser()
                    userLocalDataSource.saveUser(dto.toEntity())
                } catch (e: IOException) {
                    send(ResponseStatus.Error(e.message ?: "Failed to fetch user"))
                }
            }
        }.onStart { emit(ResponseStatus.Loading) }
            .flowOn(Dispatchers.IO)
            .catch { error ->
                emit(ResponseStatus.Error(message = error.message ?: "Unknown error"))
            }
    }
}
