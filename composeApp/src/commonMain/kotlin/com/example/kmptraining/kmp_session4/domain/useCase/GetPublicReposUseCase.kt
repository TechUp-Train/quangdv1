package com.example.kmptraining.kmp_session4.domain.useCase

import com.example.kmptraining.kmp_session4.core.utils.NetworkChecker
import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.domain.repository.PublicReposRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class GetPublicReposUseCase(
    private val repository: PublicReposRepository,
    private val networkChecker: NetworkChecker
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<ResponseStatus<List<RepoModel>>> {
        return networkChecker.observe().flatMapLatest { isConnected ->
            if (isConnected) {
                repository.getPublicRepos()
            } else {
                repository.getCachedRepos()
            }
        }
        .map { ResponseStatus.Success(it) as ResponseStatus<List<RepoModel>> }
        .onStart { emit(ResponseStatus.Loading) }
        .catch { emit(ResponseStatus.Error(it.message ?: "Unknown error")) }
    }
}