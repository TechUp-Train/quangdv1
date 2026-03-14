package com.example.kmptraining.kmp_session2.data.network

import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.data.newsData
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlin.random.Random

class RemoteDataSourceImpl : RemoteNewsDataSource {
    override fun fetchNews(): Flow<ResponseStatus<List<News>>> {
        return flow<ResponseStatus<List<News>>> {
            delay(Random.nextLong(1000, 5000))
            emit(ResponseStatus.Success(newsData))
        }
            .onStart { emit(ResponseStatus.Loading) }
            .flowOn(Dispatchers.IO)
            .catch { error ->
                emit(ResponseStatus.Error(message = error.message ?: "Unknown Error"))
            }
    }

    override fun fetchNewsDetail(newsId: Int): Flow<ResponseStatus<News>> {
        return flow {
            delay(Random.nextLong(1000, 5000))
            val news = newsData.firstOrNull { it.id == newsId }
            if (news != null) {
                emit(ResponseStatus.Success(news))
            } else {
                emit(ResponseStatus.Error(message = "News not found"))
            }
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { error ->
            emit(ResponseStatus.Error(message = error.message ?: "Unknown Error"))
        }.flowOn(Dispatchers.IO)
    }
}