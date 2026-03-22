package com.example.techup_miniproject_quangdv1.data.remote.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.dataSource.PresignDataSource
import com.example.techup_miniproject_quangdv1.data.dto.PresignLinkDto
import com.example.techup_miniproject_quangdv1.data.remote.service.ApiService

class PresignDataSourceImpl(
    private val presignService: ApiService.PresignService,
    private val uploadService: ApiService.UploadService
) : PresignDataSource {

    override suspend fun getPresignLink(): PresignLinkDto? {
        return presignService.getPresignLink().data
    }

    override suspend fun uploadImage(presignedUrl: String, imageBytes: ByteArray): Boolean {
        return try {
            uploadService.uploadImage(presignedUrl, imageBytes)
            true
        } catch (e: Exception) {
            false
        }
    }
}
