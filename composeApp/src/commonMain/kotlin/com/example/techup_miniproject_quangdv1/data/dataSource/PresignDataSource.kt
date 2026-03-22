package com.example.techup_miniproject_quangdv1.data.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.dto.PresignLinkDto


interface PresignDataSource {
    suspend fun getPresignLink(): PresignLinkDto?
    suspend fun uploadImage(presignedUrl: String, imageBytes: ByteArray): Boolean
}
