package com.example.techup_miniproject_quangdv1.presentation.screens.generatedImage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.useCase.downloadImage.DownloadUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GeneratedImageViewModel(
    private val downloadImageUseCase: DownloadUseCase,
) : ViewModel() {
    private val _downloadState = MutableStateFlow<ResponseStatus<String>>(ResponseStatus.Idle)
    val downloadState: StateFlow<ResponseStatus<String>> = _downloadState

    fun downloadImage(url: String) {
        viewModelScope.launch {
            downloadImageUseCase(url).collect { response ->
                _downloadState.value = response
            }
        }
    }

    fun resetDownloadState() {
        _downloadState.value = ResponseStatus.Idle
    }
}
