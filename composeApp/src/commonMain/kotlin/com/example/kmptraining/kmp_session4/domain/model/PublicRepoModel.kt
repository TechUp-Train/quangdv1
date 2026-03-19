package com.example.kmptraining.kmp_session4.domain.model

data class PublicRepoModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val isPrivate: Boolean,

    val htmlUrl: String?,
    val owner: OwnerModel,

    val isFork: Boolean,
)