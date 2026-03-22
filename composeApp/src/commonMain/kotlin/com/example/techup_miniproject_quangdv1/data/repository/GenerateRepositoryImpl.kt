package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.core.utils.TimestampProvider
import com.example.techup_miniproject_quangdv1.data.dataSource.GenerateDataSource
import com.example.techup_miniproject_quangdv1.data.mapper.toDomain
import com.example.techup_miniproject_quangdv1.data.mapper.toDto
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import com.example.techup_miniproject_quangdv1.domain.repository.GenerateRepository

class GenerateRepositoryImpl(
    private val dataSource: GenerateDataSource,
    private val timestampProvider: TimestampProvider
) : GenerateRepository {

    override suspend fun getTimestamp(): ResponseStatus<Long> {
        return when (val response = dataSource.getTimestamp()) {
            is ResponseStatus.Success -> {
                val serverTimestamp = response.data.timestamp
                timestampProvider.updateOffset(serverTimestamp)
                ResponseStatus.Success(serverTimestamp)
            }
            is ResponseStatus.Error -> response
            is ResponseStatus.Loading -> ResponseStatus.Loading
        }
    }

    override suspend fun generateImage(request: GenerateImageRequest): ResponseStatus<GenerateImageModel> {
        return when (val response = dataSource.generateImage(request.toDto())) {
            is ResponseStatus.Success -> ResponseStatus.Success(response.data.toDomain())
            is ResponseStatus.Error -> response
            is ResponseStatus.Loading -> ResponseStatus.Loading
        }
    }
}
