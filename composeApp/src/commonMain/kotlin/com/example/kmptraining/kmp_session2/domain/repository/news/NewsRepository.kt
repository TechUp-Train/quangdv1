package com.example.kmptraining.kmp_session2.domain.repository.news

import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews() : Flow<ResponseStatus<List<News>>>
}