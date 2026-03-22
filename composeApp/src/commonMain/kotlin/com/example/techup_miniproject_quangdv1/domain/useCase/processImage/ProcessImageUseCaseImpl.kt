package com.example.techup_miniproject_quangdv1.domain.useCase.processImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository

class ProcessImageUseCaseImpl(
    private val presignRepository: PresignRepository,
) : ProcessImageUseCase {
    override suspend fun invoke(imageBytes: ByteArray): ResponseStatus<String> {
        return try {
            val presignData = presignRepository.getPresignLink()
                ?: return ResponseStatus.Error("Could not retrieve upload permission.")

            val isUploaded = presignRepository.uploadImage(
                presignUrl = presignData.url,
                imageBytes = imageBytes
            )

            if (isUploaded) {
                ResponseStatus.Success(presignData.path)
            } else {
                ResponseStatus.Error("Image upload failed.")
            }
        } catch (e: Exception) {
            ResponseStatus.Error(e.message ?: "An unexpected error occurred during image processing.")
        }
    }
}