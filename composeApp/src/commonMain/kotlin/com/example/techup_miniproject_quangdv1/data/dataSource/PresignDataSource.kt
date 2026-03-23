package com.example.techup_miniproject_quangdv1.data.dataSource

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.dto.PresignLinkDto
import com.example.techup_miniproject_quangdv1.data.dto.TimestampDto


interface PresignDataSource {
    suspend fun getTimestamp(): TimestampDto
    suspend fun getPresignLink(): PresignLinkDto?
}
