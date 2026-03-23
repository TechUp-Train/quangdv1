package com.example.techup_miniproject_quangdv1.domain.useCase.generateImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import com.example.techup_miniproject_quangdv1.domain.repository.GenerateRepository
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository

class GenerateImageUseCaseImpl(
    private val presignRepository: PresignRepository,
    private val generateRepository: GenerateRepository,
) : GenerateImageUseCase {

    override suspend fun invoke(request: GenerateImageRequest): ResponseStatus<GenerateImageModel> {
        if (request.imageBytes.isEmpty()) return ResponseStatus.Error("Image data is empty.")

        presignRepository.getTimestamp()

        val uploaded = generateRepository.uploadImage(
            presignUrl = request.uploadUrl,
            imageBytes = request.imageBytes
        )
        if (!uploaded) return ResponseStatus.Error("Image upload failed.")

        return generateRepository.generateImage(request)
    }
}
