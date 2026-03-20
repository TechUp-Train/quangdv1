package com.example.kmptraining.kmp_session4.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.domain.model.UserModel
import com.example.kmptraining.kmp_session4.domain.repository.UserReposRepository
import com.example.kmptraining.kmp_session4.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val userReposRepository: UserReposRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<ResponseStatus<UserModel>>(ResponseStatus.Loading)
    val userState: StateFlow<ResponseStatus<UserModel>> = _userState

    private val _reposState = MutableStateFlow<ResponseStatus<List<RepoModel>>>(ResponseStatus.Loading)
    val reposState: StateFlow<ResponseStatus<List<RepoModel>>> = _reposState

    init {
        fetchProfileData()
    }

    fun fetchProfileData() {
        viewModelScope.launch {
            userRepository.getAuthenticatedUser().collect { status ->
                _userState.value = status
            }
        }
        viewModelScope.launch {
            userReposRepository.getUserRepos().collect { status ->
                _reposState.value = status
            }
        }
    }
}