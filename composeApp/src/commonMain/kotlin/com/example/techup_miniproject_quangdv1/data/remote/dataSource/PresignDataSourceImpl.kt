package com.example.techup_miniproject_quangdv1.data.remote.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.Log
import com.example.techup_miniproject_quangdv1.data.dataSource.PresignDataSource
import com.example.techup_miniproject_quangdv1.data.dto.PresignLinkDto
import com.example.techup_miniproject_quangdv1.data.dto.TimestampDto
import com.example.techup_miniproject_quangdv1.data.remote.service.ApiService

class PresignDataSourceImpl(
    private val timestampService: ApiService.TimestampService,
    private val presignService: ApiService.PresignService,
) : PresignDataSource {
    override suspend fun getTimestamp(): TimestampDto = timestampService.getTimestamp()

    override suspend fun getPresignLink(): PresignLinkDto? =
        try {
            presignService.getPresignLink().data
        } catch (e: Exception) {
            Log.e(Log.DATA_SOURCE, "Error get presign link: ${e.message}")
            null
        }
}
