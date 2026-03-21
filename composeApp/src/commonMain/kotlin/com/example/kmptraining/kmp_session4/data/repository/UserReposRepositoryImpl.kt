package com.example.kmptraining.kmp_session4.data.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.dataSource.userRepos.UserReposLocalDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.userRepos.UserReposRemoteDataSource
import com.example.kmptraining.kmp_session4.data.mapper.toEntity
import com.example.kmptraining.kmp_session4.data.mapper.toModel
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.domain.repository.UserReposRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.io.IOException

class UserReposRepositoryImpl(
    private val userReposLocalDataSource: UserReposLocalDataSource,
    private val userReposRemoteDataSource: UserReposRemoteDataSource,
) : UserReposRepository {
    override fun getUserRepos(): Flow<ResponseStatus<List<RepoModel>>> {
        return channelFlow {
            launch {
                try {
                    val dto = userReposRemoteDataSource.fetchUserRepos()
                    userReposLocalDataSource.saveRepos(dto.map { it.toEntity("OWNED") })
                } catch (e: Exception) {
                    send(ResponseStatus.Error(e.message ?: "Failed to fetch user repos"))
                }
            }

            userReposLocalDataSource.observeRepos().collect { entityList ->
                // The original code had entityList?.let { ... } ?: run { ... }
                // The provided snippet changes this to if (!entityList.isNullOrEmpty()) { ... } else { ... }
                // Applying the logic from the provided snippet, assuming 'entityList' can be null or empty
                if (!entityList.isNullOrEmpty()) {
                    send(ResponseStatus.Success(entityList.map { it.toModel() }))
                } else {
                    send(ResponseStatus.Loading)
                }
            }

        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { error ->
            emit(ResponseStatus.Error(message = error.message ?: "Unknown error"))
        }.flowOn(Dispatchers.IO)
    }
}