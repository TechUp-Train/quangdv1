package com.example.techup_miniproject_quangdv1.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenerateImageResponseDto(
    val url: String,
    val path: String,
)
