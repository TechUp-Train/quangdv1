package com.example.techup_miniproject_quangdv1.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PresignLinkDto(
    val url: String,
    val path: String
)
