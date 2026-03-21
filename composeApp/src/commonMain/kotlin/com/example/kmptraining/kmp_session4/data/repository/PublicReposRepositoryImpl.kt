package com.example.kmptraining.kmp_session4.data.repository

import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposLocalDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposRemoteDataSource
import com.example.kmptraining.kmp_session4.data.mapper.toEntity
import com.example.kmptraining.kmp_session4.data.mapper.toModel
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.domain.repository.PublicReposRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import co.touchlab.kermit.Logger

class PublicReposRepositoryImpl(
    private val publicReposLocalDataSource: PublicReposLocalDataSource,
    private val publicReposRemoteDataSource: PublicReposRemoteDataSource,
) : PublicReposRepository {
    override fun getPublicRepos(): Flow<List<RepoModel>> {
        return channelFlow {
            launch {
                try {
                    val dtos = publicReposRemoteDataSource.fetchPublicRepos()
                    publicReposLocalDataSource.saveRepos(dtos.map { it.toEntity("PUBLIC") })
                } catch (e: Exception) {
                    Logger.withTag("PublicReposRepository").e(e) { "Error fetching public repos" }
                }
            }

            publicReposLocalDataSource.observeRepos().collect { entityList ->
                if (!entityList.isNullOrEmpty()) {
                    send(entityList.map { it.toModel() })
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getCachedRepos(): Flow<List<RepoModel>> {
        return publicReposLocalDataSource.observeRepos()
            .map { entityList -> entityList?.map { it.toModel() } ?: emptyList() }
            .flowOn(Dispatchers.IO)
    }

    override fun searchRepos(
        query: String,
        sort: String?,
        order: String?,
        perPage: Int,
        page: Int
    ): Flow<List<RepoModel>> {
        return flow {
            val result = publicReposRemoteDataSource.searchRepos(
                query,
                sort,
                order,
                perPage,
                page
            )
            val models = result.items?.map { it.toModel() } ?: emptyList()
            emit(models)
        }.flowOn(Dispatchers.IO)
    }
}