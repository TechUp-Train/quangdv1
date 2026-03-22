package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.dataSource.PresignDataSource
import com.example.techup_miniproject_quangdv1.data.mapper.toDomain
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository

class PresignRepositoryImpl(
    private val dataSource: PresignDataSource
) : PresignRepository {

    override suspend fun getPresignLink(): PresignLinkModel? {
        return dataSource.getPresignLink()?.toDomain()
    }

    override suspend fun uploadImage(presignUrl: String, imageBytes: ByteArray): Boolean {
        return dataSource.uploadImage(presignUrl, imageBytes)
    }
}
