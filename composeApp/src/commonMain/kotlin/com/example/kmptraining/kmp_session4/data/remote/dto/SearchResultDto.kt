package com.example.kmptraining.kmp_session4.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultDto(
    @SerialName("total_count") val totalCount: Int? = null,
    @SerialName("incomplete_results") val incompleteResults: Boolean? = null,
    @SerialName("items") val items: List<RepoDto>? = null
)