package com.example.techup_miniproject_quangdv1.data.remote.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.Log
import com.example.techup_miniproject_quangdv1.data.dataSource.GenerateDataSource
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageRequestDto
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageResponseDto
import com.example.techup_miniproject_quangdv1.data.remote.service.ApiService

class GenerateDataSourceImpl(
    private val uploadService: ApiService.UploadService,
    private val generateService: ApiService.GenerateService
) : GenerateDataSource {
    override suspend fun uploadImage(presignedUrl: String, imageBytes: ByteArray): Boolean {
        return try {
            uploadService.uploadImage(presignedUrl, imageBytes)
            true
        } catch (e: Exception) {
            Log.e(Log.DATA_SOURCE, "Error uploading image: ${e.message}")
            false
        }
    }

    override suspend fun generateImage(request: GenerateImageRequestDto): GenerateImageResponseDto? {
        return  generateService.generateImage(request).data
    }
}
