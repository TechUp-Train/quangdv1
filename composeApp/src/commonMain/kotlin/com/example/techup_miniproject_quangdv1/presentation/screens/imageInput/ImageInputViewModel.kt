package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techup_miniproject_quangdv1.core.utils.ImageMode
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.CategoriesItemModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import com.example.techup_miniproject_quangdv1.domain.useCase.convertImage.ConvertImageUseCase
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
    private val convertImageUseCase: ConvertImageUseCase,
) : ViewModel() {

    // Data to be displayed in the UI
    private val _imageMode = MutableStateFlow(ImageMode.IMAGE_EDITING)
    val imageMode: StateFlow<ImageMode> = _imageMode

    private val _stylesState = MutableStateFlow<ResponseStatus<List<CategoriesItemModel>>>(ResponseStatus.Loading)
    val stylesState: StateFlow<ResponseStatus<List<CategoriesItemModel>>> = _stylesState

    private val _selectedCategoryIndex = MutableStateFlow(0)
    val selectedCategoryIndex: StateFlow<Int> = _selectedCategoryIndex

    private val _selectedStyleId = MutableStateFlow<String?>(null)
    val selectedStyleId: StateFlow<String?> = _selectedStyleId

    // Data to generate image
    private val _selectedImages = MutableStateFlow<List<PlatformImage>>(emptyList())
    val selectedImages: StateFlow<List<PlatformImage>> = _selectedImages

    private val _promptText = MutableStateFlow("")
    val promptText: StateFlow<String> = _promptText

    private val _generateProcessStatus = MutableStateFlow<ResponseStatus<String>>(ResponseStatus.Idle)
    val generateProcessStatus: StateFlow<ResponseStatus<String>> = _generateProcessStatus

    init {
        fetchStyles()
    }

    fun setImageMode(mode: ImageMode) {
        _imageMode.value = mode
    }

    fun setPromptText(text: String) {
        _promptText.value = text
    }

    fun resetGenerateStatus() {
        _generateProcessStatus.value = ResponseStatus.Idle
    }

    fun fetchStyles() {
        viewModelScope.launch {
            getStyleUseCase.invoke().collect { styles ->
                _stylesState.value = styles
                if (styles is ResponseStatus.Success && styles.data.isNotEmpty()) {
                    _selectedCategoryIndex.value = 0
                    _selectedStyleId.value = null
                }
            }
        }
    }

    fun selectCategory(index: Int) {
        _selectedCategoryIndex.value = index
        _selectedStyleId.value = null
    }

    fun selectStyle(styleId: String) {
        _selectedStyleId.value = styleId
    }

    fun setSelectedImages(images: List<PlatformImage>) {
        _selectedImages.value = images
    }

    fun processImage() {
        if (_selectedImages.value.isEmpty()) {
            _generateProcessStatus.value = ResponseStatus.Error("Please select at least one image.")
            return
        }

        viewModelScope.launch {
            val currentMode = _imageMode.value
            val selectedImages = _selectedImages.value

            processImageUseCase.invoke(selectedImages.size).collect { processResponse ->
                when (processResponse) {
                    is ResponseStatus.Loading -> {
                        _generateProcessStatus.value = ResponseStatus.Loading
                    }

                    is ResponseStatus.Error -> {
                        _generateProcessStatus.value = ResponseStatus.Error(processResponse.message)
                    }

                    is ResponseStatus.Success -> {
                        val presignLinks = processResponse.data
                        val imageBytes = convertImageUseCase.invoke(selectedImages)

                        val generateImageRequest = GenerateImageRequest(
                            uploadUrls = presignLinks.map { it.url },
                            filePaths = presignLinks.map { it.path },
                            imageBytes = imageBytes,
                            mode = currentMode.name,
                            positivePrompt = _promptText.value,
                        )
                        generateImage(generateImageRequest)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun generateImage(imageRequest: GenerateImageRequest) {
        viewModelScope.launch {
            generateImageUseCase.invoke(imageRequest).collect { generateResponse ->
                when(generateResponse) {
                    is ResponseStatus.Loading -> {
                        _generateProcessStatus.value = ResponseStatus.Loading
                    }
                    is ResponseStatus.Error -> {
                        _generateProcessStatus.value = ResponseStatus.Error(generateResponse.message)
                    }
                    is ResponseStatus.Success -> {
                        _generateProcessStatus.value = ResponseStatus.Success(generateResponse.data.url)
                    }
                    else -> {}
                }
            }
        }
    }

    fun clearSelectedImages() {
        _selectedImages.value = emptyList()
    }

    override fun onCleared() {
        super.onCleared()
        clearSelectedImages()
    }
}
