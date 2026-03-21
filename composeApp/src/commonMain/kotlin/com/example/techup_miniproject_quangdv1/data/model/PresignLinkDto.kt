package com.example.techup_miniproject_quangdv1.data.model

import kotlinx.serialization.Serializable

/**
 * Response data DTO for the Pre-Signed Link API.
 * Endpoint: GET /api/v5.1/qwen-editing/presigned-link
 *
 * @property url The temporary pre-signed URL for uploading an image to S3.
 * @property path The storage path of the uploaded file (used as input for Generate API).
 */
@Serializable
data class PresignLinkDto(
    val url: String,
    val path: String
)
