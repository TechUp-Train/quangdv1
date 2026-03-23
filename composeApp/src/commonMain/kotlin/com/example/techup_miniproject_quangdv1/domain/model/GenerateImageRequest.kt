package com.example.techup_miniproject_quangdv1.domain.model

data class GenerateImageRequest(
    val uploadUrl: String,
    val imageBytes: ByteArray,
    val filePath: String,
    val mode: String? = null,
    val positivePrompt: String? = null
)
