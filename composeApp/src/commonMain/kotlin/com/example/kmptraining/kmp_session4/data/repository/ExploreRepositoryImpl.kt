package com.example.kmptraining.kmp_session4.data.repository

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.remote.dto.PublicRepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubService
import com.example.kmptraining.kmp_session4.domain.repository.ExploreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class ExploreRepositoryImpl(
    private val githubService: GithubService,
) : ExploreRepository {
    override suspend fun getPublicRepos(): Flow<ResponseStatus<List<PublicRepoDto>>> {
        return flow<ResponseStatus<List<PublicRepoDto>>> {
            emit(ResponseStatus.Success(githubService.getPublicRepos()))
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(e.message ?: "Unknown Error"))
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
                    githubService.searchRepos(
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