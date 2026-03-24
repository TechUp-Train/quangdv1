package com.example.techup_miniproject_quangdv1.data.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageRequestDto
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageResponseDto
import com.example.techup_miniproject_quangdv1.data.dto.TimestampDto

interface GenerateDataSource {
    suspend fun uploadImage(presignedUrl: String, imageBytes: ByteArray): Boolean
    suspend fun generateImage(request: GenerateImageRequestDto): GenerateImageResponseDto?
}
