package com.example.kmptraining.kmp_session4.domain.usecase

import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.domain.repository.PublicReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class SearchReposUseCase(
    private val repository: PublicReposRepository
) {
    operator fun invoke(
        query: String,
        sort: String? = null,
        order: String? = null,
        perPage: Int = 30,
        page: Int = 1
    ): Flow<ResponseStatus<List<RepoModel>>> {
        return repository.searchRepos(query, sort, order, perPage, page)
            .map { ResponseStatus.Success(it) as ResponseStatus<List<RepoModel>> }
            .onStart { emit(ResponseStatus.Loading) }
            .catch { emit(ResponseStatus.Error(it.message ?: "Unknown error")) }
    }
}
