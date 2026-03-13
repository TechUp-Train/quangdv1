package com.example.kmptraining.kmp_session2.data.network

import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class RemoteDataSourceImpl : RemoteNewsDataSource {
    override fun fetchNews(): Flow<ResponseStatus<List<News>>> {
        return flow<ResponseStatus<List<News>>> {
            emit(ResponseStatus.Success(listOf()))
        }.onStart { emit(ResponseStatus.Loading) }.flowOn(Dispatchers.IO).catch { error ->
            emit(ResponseStatus.Error(message = error.message ?: "Unknown Error"))
        }
    }
}