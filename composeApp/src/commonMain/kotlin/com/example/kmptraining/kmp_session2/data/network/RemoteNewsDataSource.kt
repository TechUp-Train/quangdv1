package com.example.kmptraining.kmp_session2.data.network

import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface RemoteNewsDataSource {
    fun fetchNews() : Flow<ResponseStatus<List<News>>>
}