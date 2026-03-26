package com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techup_miniproject_quangdv1.core.utils.GalleryImageSource
import com.example.techup_miniproject_quangdv1.core.utils.Log
import com.example.techup_miniproject_quangdv1.core.utils.MediaPermissionManager
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.useCase.pickImages.PickImagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val pickImagesUseCase: PickImagesUseCase
) : ViewModel() {
    private val _imagesState = MutableStateFlow<ResponseStatus<List<PlatformImage>>>(ResponseStatus.Loading())
    val imagesState: StateFlow<ResponseStatus<List<PlatformImage>>> = _imagesState

    fun loadImages(
        permissionManager: MediaPermissionManager,
        galleryImageSource: GalleryImageSource
    ) {
        viewModelScope.launch {
            pickImagesUseCase(permissionManager, galleryImageSource).collect { response ->
                _imagesState.value = response
                when (response) {
                    is ResponseStatus.Success -> {
                        Log.d(Log.VIEW_MODEL, "Images loaded successfully: ${response.data.size} images")
                    }
                    is ResponseStatus.Error -> {
                        Log.e(Log.VIEW_MODEL, "Failed to load images: ${response.message}")
                    }
                    is ResponseStatus.Loading -> {
                        Log.d(Log.VIEW_MODEL, "Image loading in progress...")
                    }
                    else -> {}
                }
            }
        }
    }
}
