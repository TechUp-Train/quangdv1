package com.example.techup_miniproject_quangdv1.domain.model

/**
 * Domain-level request model for image generation.
 *
 * @property files          List of uploaded file paths (obtained from presign/upload flow).
 * @property mode           Editing mode, e.g. "IMAGE_EDITING" (1 image), "COMBINE_IMAGES" (2 images).
 * @property positivePrompt User-provided prompt describing the desired image style/content. Max 2000 chars.
 */
data class GenerateImageRequest(
    val files: List<String>,
    val mode: String? = null,
    val positivePrompt: String? = null
)
