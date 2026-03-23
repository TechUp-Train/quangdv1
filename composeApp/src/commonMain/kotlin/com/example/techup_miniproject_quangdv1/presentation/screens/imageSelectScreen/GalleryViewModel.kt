package com.example.techup_miniproject_quangdv1.presentation.screens.imageSelectScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.useCase.pickImages.PickImagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val pickImagesUseCase: PickImagesUseCase
) : ViewModel() {
    private val _imagesState = MutableStateFlow<ResponseStatus<List<PlatformImage>>>(ResponseStatus.Loading)
    val imagesState: StateFlow<ResponseStatus<List<PlatformImage>>> = _imagesState

    init {
        loadImages()
    }

    fun loadImages() {
        viewModelScope.launch {
            pickImagesUseCase().collect { response ->
                _imagesState.value = response
            }
        }
    }
}