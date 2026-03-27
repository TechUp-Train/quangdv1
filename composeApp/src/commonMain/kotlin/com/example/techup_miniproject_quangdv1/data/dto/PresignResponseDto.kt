package com.example.techup_miniproject_quangdv1.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PresignResponseDto<T>(
    val statusCode: Int,
    val message: String,
    val data: T? = null,
    val timestamp: Long? = null,
)
