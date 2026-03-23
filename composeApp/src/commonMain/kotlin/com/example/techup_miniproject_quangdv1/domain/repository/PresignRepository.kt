package com.example.techup_miniproject_quangdv1.domain.repository

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel

interface PresignRepository {
    suspend fun getTimestamp(): Long

    suspend fun getPresignLink(): PresignLinkModel?
}