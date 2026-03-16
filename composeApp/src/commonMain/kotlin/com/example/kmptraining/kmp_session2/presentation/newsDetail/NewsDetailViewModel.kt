package com.example.kmptraining.kmp_session2.presentation.newsDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.repository.news.NewsRepository
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsDetailViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _newsState = MutableStateFlow<ResponseStatus<News>>(ResponseStatus.Loading)
    val newsState: StateFlow<ResponseStatus<News>> = _newsState.asStateFlow()

    fun fetchNews(newsId: Int) {
        viewModelScope.launch {
            newsRepository.getNewsDetail(newsId).collect { result ->
                _newsState.value = result
            }
        }
    }

    fun bookmarkNews(news: News) {}

    fun shareNews(news: News) {}
}