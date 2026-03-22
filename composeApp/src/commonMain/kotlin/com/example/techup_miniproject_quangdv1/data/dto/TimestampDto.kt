package com.example.techup_miniproject_quangdv1.data.dto

import kotlinx.serialization.Serializable

/**
 * Response DTO for the Timestamp API.
 * Endpoint: GET https://video-gen-core.aperogroup.ai/timestamp
 */
@Serializable
data class TimestampDto(
    val timestamp: Long
)
