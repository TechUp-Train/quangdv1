package com.example.techup_miniproject_quangdv1.domain.model

/**
 * Domain model for the generated AI image result.
 *
 * @property url  Public URL of the generated output image.
 * @property path Storage path of the generated image on the server.
 */
data class GenerateImageModel(
    val url: String,
    val path: String,
)
