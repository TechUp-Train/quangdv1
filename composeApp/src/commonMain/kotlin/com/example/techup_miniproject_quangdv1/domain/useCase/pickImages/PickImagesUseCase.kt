package com.example.techup_miniproject_quangdv1.domain.useCase.pickImages

import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface PickImagesUseCase {
    suspend operator fun invoke(): Flow<ResponseStatus<List<PlatformImage>>>
}