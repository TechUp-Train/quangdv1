package com.example.techup_miniproject_quangdv1.data.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageRequestDto
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageResponseDto
import com.example.techup_miniproject_quangdv1.data.dto.TimestampDto

/**
 * Interface for the Generate Image and Timestamp data sources.
 */
interface GenerateDataSource {
    suspend fun getTimestamp(): ResponseStatus<TimestampDto>
    suspend fun generateImage(request: GenerateImageRequestDto): ResponseStatus<GenerateImageResponseDto>
}
