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
) : GenerateRepository {
    override suspend fun uploadImage(presignUrl: String, imageBytes: ByteArray): Boolean {
        return dataSource.uploadImage(presignUrl, imageBytes)
    }

    override suspend fun generateImage(request: GenerateImageRequest): GenerateImageModel? {
        return dataSource.generateImage(request.toDto())?.toDomain()
    }
}
