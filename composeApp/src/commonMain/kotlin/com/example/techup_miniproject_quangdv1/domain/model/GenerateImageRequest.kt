package com.example.techup_miniproject_quangdv1.domain.model

data class GenerateImageRequest(
    val uploadUrls: List<String>,
    val imageBytes: List<ByteArray>,
    val filePaths: List<String>,
    val mode: String? = null,
    val positivePrompt: String? = null
)
