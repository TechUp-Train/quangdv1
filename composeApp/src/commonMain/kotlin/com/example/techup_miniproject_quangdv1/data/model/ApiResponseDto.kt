package com.example.techup_miniproject_quangdv1.data.model

import kotlinx.serialization.Serializable

/**
 * Generic API response wrapper matching the server's standard response format.
 * Used by Presign Link and Generate Image endpoints.
 */
@Serializable
data class ApiResponseDto<T>(
    val statusCode: Int,
    val message: String,
    val data: T? = null,
    val timestamp: Long? = null
)
