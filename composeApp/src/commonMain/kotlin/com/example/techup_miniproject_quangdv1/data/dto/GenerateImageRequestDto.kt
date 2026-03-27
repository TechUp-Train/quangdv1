package com.example.techup_miniproject_quangdv1.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenerateImageRequestDto(
    val files: List<String>,
    val mode: String? = null,
    val positivePrompt: String? = null,
    val ratio: String? = null,
    val acceptNSFW: Boolean? = null,
)
