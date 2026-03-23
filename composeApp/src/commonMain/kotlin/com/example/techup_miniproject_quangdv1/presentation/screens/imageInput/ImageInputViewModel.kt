package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.CategoriesItemModel
import com.example.techup_miniproject_quangdv1.domain.useCase.generateImage.GenerateImageUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.getStyles.GetStyleUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.processImage.ProcessImageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImageInputViewModel(
    private val generateImageUseCase: GenerateImageUseCase,
    private val processImageUseCase: ProcessImageUseCase,
    private val getStyleUseCase: GetStyleUseCase,
) : ViewModel() {
    private val _stylesState = MutableStateFlow<ResponseStatus<List<CategoriesItemModel>>>(ResponseStatus.Loading)
    val stylesState: StateFlow<ResponseStatus<List<CategoriesItemModel>>> = _stylesState

    init {
        fetchStyles()
    }

    fun fetchStyles() {
        viewModelScope.launch {
            getStyleUseCase.invoke().collect { styles ->
                _stylesState.value = styles
            }
        }
    }
}