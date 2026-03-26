package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.core.utils.Log
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
        return try {
            dataSource.uploadImage(presignUrl, imageBytes)
        } catch (e: Exception) {
            Log.e(Log.REPOSITORY, "Error uploading image: ${e.message}")
            false
        }
    }

    override suspend fun generateImage(request: GenerateImageRequest): GenerateImageModel? {
        return try {
            dataSource.generateImage(request.toDto())?.toDomain()
        } catch (e: Exception) {
            Log.e(Log.REPOSITORY, "Error generating image: ${e.message}")
            null
        }
    }
}
