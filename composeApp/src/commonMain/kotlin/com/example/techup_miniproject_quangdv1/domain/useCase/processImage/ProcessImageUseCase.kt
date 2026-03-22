package com.example.techup_miniproject_quangdv1.domain.useCase.processImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus

interface ProcessImageUseCase {
    suspend operator fun invoke(imageBytes: ByteArray): ResponseStatus<String>
}