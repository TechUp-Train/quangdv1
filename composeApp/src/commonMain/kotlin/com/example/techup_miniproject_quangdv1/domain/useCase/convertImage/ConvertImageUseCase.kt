package com.example.techup_miniproject_quangdv1.domain.useCase.convertImage

import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage

interface ConvertImageUseCase {
    suspend operator fun invoke(image: List<PlatformImage>): List<ByteArray>
}