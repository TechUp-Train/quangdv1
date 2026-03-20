package com.example.kmptraining.kmp_session4.data.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposLocalDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposRemoteDataSource
import com.example.kmptraining.kmp_session4.data.mapper.toEntity
import com.example.kmptraining.kmp_session4.data.mapper.toModel
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
import com.example.kmptraining.kmp_session4.domain.model.PublicRepoModel
import com.example.kmptraining.kmp_session4.domain.repository.PublicReposRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PublicReposRepositoryImpl(
    private val publicReposLocalDataSource: PublicReposLocalDataSource,
    private val publicReposRemoteDataSource: PublicReposRemoteDataSource,
) : PublicReposRepository {
    override fun getPublicRepos(): Flow<ResponseStatus<List<PublicRepoModel>>> {
        return channelFlow {
            launch {
                try {
                    val dtos = publicReposRemoteDataSource.fetchPublicRepos()
                    publicReposLocalDataSource.saveRepos(dtos.map { it.toEntity() })
                } catch (e: Exception) {
                    send(ResponseStatus.Error(e.message ?: "Failed to fetch public repos"))
                }
            }

            publicReposLocalDataSource.observeRepos().collect { entityList ->
                entityList?.let { localDataList ->
                    send(ResponseStatus.Success(localDataList.map { it.toModel() }))
                }
            }
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { error ->
            emit(ResponseStatus.Error(message = error.message ?: "Unknown error"))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchRepos(
        query: String,
        sort: String?,
        order: String?,
        perPage: Int,
        page: Int
    ): Flow<ResponseStatus<SearchResultDto>> {
        return flow<ResponseStatus<SearchResultDto>> {
            emit(
                ResponseStatus.Success(
                    publicReposRemoteDataSource.searchRepos(
                        query,
                        sort,
                        order,
                        perPage,
                        page
                    )
                )
            )
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(e.message ?: "Unknown Error"))
        }
    }
}