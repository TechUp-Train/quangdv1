package com.example.techup_miniproject_quangdv1.domain.useCase.processImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel
import kotlinx.coroutines.flow.Flow

interface ProcessImageUseCase {
    suspend operator fun invoke(imageCount: Int = 1): Flow<ResponseStatus<List<PresignLinkModel>>>
}
