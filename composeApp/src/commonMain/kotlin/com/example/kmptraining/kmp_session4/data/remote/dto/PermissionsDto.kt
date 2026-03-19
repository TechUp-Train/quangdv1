package com.example.kmptraining.kmp_session4.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PermissionsDto(
    @SerialName("admin") val admin: Boolean? = null,
    @SerialName("maintain") val maintain: Boolean? = null,
    @SerialName("push") val push: Boolean? = null,
    @SerialName("triage") val triage: Boolean? = null,
    @SerialName("pull") val pull: Boolean? = null
)
