package com.example.kmptraining.kmp_session2.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.repository.news.NewsRepository
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _newsState = MutableStateFlow<ResponseStatus<List<News>>>(ResponseStatus.Loading)
    val newsState : StateFlow<ResponseStatus<List<News>>> = _newsState.asStateFlow()

    fun getNews() {
        viewModelScope.launch {
            newsRepository.getNews().collect { result ->
                _newsState.value = result
            }
        }
    }
}