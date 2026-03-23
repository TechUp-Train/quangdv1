package com.example.techup_miniproject_quangdv1.data.remote.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.dataSource.PresignDataSource
import com.example.techup_miniproject_quangdv1.data.dto.PresignLinkDto
import com.example.techup_miniproject_quangdv1.data.dto.TimestampDto
import com.example.techup_miniproject_quangdv1.data.remote.service.ApiService

class PresignDataSourceImpl(
    private val timestampService: ApiService.TimestampService,
    private val presignService: ApiService.PresignService,
) : PresignDataSource {
    override suspend fun getTimestamp(): TimestampDto {
        return timestampService.getTimestamp()
    }

    override suspend fun getPresignLink(): PresignLinkDto? {
        return presignService.getPresignLink().data
    }
}
