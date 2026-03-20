package com.example.kmptraining.kmp_session4.presentation.screens.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.data.mapper.toModel
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.domain.repository.PublicReposRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(FlowPreview::class)
class ExploreViewModel(
    private val publicReposRepository: PublicReposRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _selectedTab = MutableStateFlow(SearchTab.REPOSITORIES)
    val selectedTab: StateFlow<SearchTab> = _selectedTab.asStateFlow()

    val searchResults: StateFlow<ResponseStatus<List<RepoModel>>> = _searchText
        .debounce(500L)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) {
                publicReposRepository.getPublicRepos()
            } else {
                publicReposRepository.searchRepos(query).map { status ->
                    when (status) {
                        is ResponseStatus.Success -> {
                            val models = status.data.items?.map { it.toModel() } ?: emptyList()
                            ResponseStatus.Success(models)
                        }
                        is ResponseStatus.Error -> ResponseStatus.Error(status.message)
                        is ResponseStatus.Loading -> ResponseStatus.Loading
                    }
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ResponseStatus.Loading
        )


    fun onSearchQueryChanged(query: String) {
        _searchText.value = query
    }

    fun onTabSelected(tab: SearchTab) {
        _selectedTab.value = tab
    }
}

enum class SearchTab(val title: String) {
    REPOSITORIES("Repositories"),
    USERS("Users"),
    ISSUES("Issues"),
    PULL_REQUESTS("Pull Requests")
}