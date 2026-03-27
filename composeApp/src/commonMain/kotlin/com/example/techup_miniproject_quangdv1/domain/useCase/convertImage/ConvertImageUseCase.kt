package com.example.techup_miniproject_quangdv1.domain.useCase.convertImage

import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus

interface ConvertImageUseCase {
    suspend operator fun invoke(imageList: List<PlatformImage>): ResponseStatus<List<ByteArray>>
}
