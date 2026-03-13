package com.example.kmptraining.kmp_session2.domain.repository.news

import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.data.network.RemoteNewsDataSource
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsDataSource: RemoteNewsDataSource
) : NewsRepository {
    override fun getNews(): Flow<ResponseStatus<List<News>>> {
        return newsDataSource.fetchNews()
    }
}