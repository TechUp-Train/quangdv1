package com.example.techup_miniproject_quangdv1.data.model

import kotlinx.serialization.Serializable

/**
 * Request body DTO for the Generate Image (Qwen Editing) API.
 * Endpoint: POST /api/v5.1/qwen-editing
 *
 * @property files List of image file paths (from presign upload).
 * @property mode Editing mode: "IMAGE_EDITING" (1 image) or "COMBINE_IMAGES" (2 images), etc.
 * @property positivePrompt Optional prompt describing desired output attributes. Max 2000 chars.
 * @property ratio Optional image aspect ratio.
 * @property acceptNSFW Optional flag to accept NSFW content.
 */
@Serializable
data class GenerateImageRequestDto(
    val files: List<String>,
    val mode: String? = null,
    val positivePrompt: String? = null,
    val ratio: String? = null,
    val acceptNSFW: Boolean? = null
)

/**
 * Response data DTO for the Generate Image API.
 *
 * @property url URL of the generated output image.
 * @property path Storage path of the generated image.
 */
@Serializable
data class GenerateImageResponseDto(
    val url: String,
    val path: String
)
