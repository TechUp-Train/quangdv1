package com.example.techup_miniproject_quangdv1.domain.repository

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest

interface GenerateRepository {
    suspend fun uploadImage(
        presignUrl: String,
        imageBytes: ByteArray,
    ): Boolean

    suspend fun generateImage(request: GenerateImageRequest): GenerateImageModel?
}
