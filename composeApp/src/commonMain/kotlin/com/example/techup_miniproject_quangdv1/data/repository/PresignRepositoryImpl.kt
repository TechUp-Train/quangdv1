package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.core.utils.TimestampProvider
import com.example.techup_miniproject_quangdv1.data.dataSource.PresignDataSource
import com.example.techup_miniproject_quangdv1.data.mapper.toDomain
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository

class PresignRepositoryImpl(
    private val dataSource: PresignDataSource,
    private val timestampProvider: TimestampProvider,
) : PresignRepository {
    override suspend fun getTimestamp(): Long {
        val response = dataSource.getTimestamp()
        val serverTimestamp = response.timestamp
        timestampProvider.updateOffset(serverTimestamp)
        return serverTimestamp
    }

    override suspend fun getPresignLink(): PresignLinkModel? = dataSource.getPresignLink()?.toDomain()
}
