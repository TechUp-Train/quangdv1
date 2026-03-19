package com.example.kmptraining.kmp_session4.data.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.local.dao.UserDao
import com.example.kmptraining.kmp_session4.data.local.dao.UserRepoDao
import com.example.kmptraining.kmp_session4.data.remote.dto.UserRepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.UserDto
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubService
import com.example.kmptraining.kmp_session4.domain.dataSource.LocalDataSource
import com.example.kmptraining.kmp_session4.domain.dataSource.RemoteDataSource
import com.example.kmptraining.kmp_session4.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class UserRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : UserRepository {
    override suspend fun getUser(): Flow<ResponseStatus<UserDto>> {
        return flow<ResponseStatus<UserDto>> {
            emit(
//                ResponseStatus.Success(githubService.getUser())
                ResponseStatus.Loading
            )
        }.flowOn(Dispatchers.IO)
            .onStart { emit(ResponseStatus.Loading) }
            .catch { e -> emit(ResponseStatus.Error(e.message ?: "Unknown Error")) }
    }

    override suspend fun getUserRepos(): Flow<ResponseStatus<List<UserRepoDto>>> {
        return flow<ResponseStatus<List<UserRepoDto>>> {
            emit(
//                ResponseStatus.Success(githubService.getUserRepos())
                ResponseStatus.Loading
            )
        }.flowOn(Dispatchers.IO)
            .onStart { emit(ResponseStatus.Loading) }
            .catch { e -> emit(ResponseStatus.Error(e.message ?: "Unknown Error")) }
    }
}
