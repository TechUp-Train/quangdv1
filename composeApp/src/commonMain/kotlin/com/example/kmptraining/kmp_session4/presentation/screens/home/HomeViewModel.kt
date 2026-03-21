package com.example.kmptraining.kmp_session4.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.domain.useCase.GetPublicReposUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPublicReposUseCase: GetPublicReposUseCase
) : ViewModel() {
    private val _publicRepos = MutableStateFlow<ResponseStatus<List<RepoModel>>>(ResponseStatus.Loading)
    val publicRepos: StateFlow<ResponseStatus<List<RepoModel>>> = _publicRepos

    init {
        fetchPublicRepos()
    }

    fun fetchPublicRepos() {
        viewModelScope.launch {
            getPublicReposUseCase().collect { response ->
                _publicRepos.value = response
            }
        }
    }
}