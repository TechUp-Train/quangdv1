package com.example.techup_miniproject_quangdv1.data.remote.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.dataSource.GenerateDataSource
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageRequestDto
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageResponseDto
import com.example.techup_miniproject_quangdv1.data.dto.TimestampDto
import com.example.techup_miniproject_quangdv1.data.remote.service.ApiService

/**
 * Implementation of [GenerateDataSource] using Ktor [ApiService].
 */
class GenerateDataSourceImpl(
    private val timestampService: ApiService.TimestampService,
    private val generateService: ApiService.GenerateService
) : GenerateDataSource {

    override suspend fun getTimestamp(): ResponseStatus<TimestampDto> {
        return try {
            ResponseStatus.Success(timestampService.getTimestamp())
        } catch (e: Exception) {
            ResponseStatus.Error(e.message ?: "Unknown error fetching timestamp")
        }
    }

    override suspend fun generateImage(request: GenerateImageRequestDto): ResponseStatus<GenerateImageResponseDto> {
        return try {
            val response = generateService.generateImage(request)
            if (response.statusCode == 200 && response.data != null) {
                ResponseStatus.Success(response.data)
            } else {
                ResponseStatus.Error(response.message, response.statusCode)
            }
        } catch (e: Exception) {
            ResponseStatus.Error(e.message ?: "Unknown error generating image")
        }
    }
}
