package com.example.techup_miniproject_quangdv1.domain.useCase.generateImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import kotlinx.coroutines.flow.Flow

interface GenerateImageUseCase {
    suspend operator fun invoke(request: GenerateImageRequest): Flow<ResponseStatus<GenerateImageModel>>
}
