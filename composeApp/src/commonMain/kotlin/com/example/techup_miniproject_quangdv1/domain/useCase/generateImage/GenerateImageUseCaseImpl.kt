package com.example.techup_miniproject_quangdv1.domain.useCase.generateImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import com.example.techup_miniproject_quangdv1.domain.repository.GenerateRepository

class GenerateImageUseCaseImpl(
    private val generateRepository: GenerateRepository
) : GenerateImageUseCase {
    
    override suspend fun invoke(request: GenerateImageRequest): ResponseStatus<GenerateImageModel> {
        // Step 1: Synchronize time (updates TimestampProvider automatically)
        val timestampResponse = generateRepository.getTimestamp()
        if (timestampResponse is ResponseStatus.Error) {
            return ResponseStatus.Error("Time synchronization failed: ${timestampResponse.message}")
        }

        // Step 2: Trigger the actual generation
        return generateRepository.generateImage(request)
    }
}
