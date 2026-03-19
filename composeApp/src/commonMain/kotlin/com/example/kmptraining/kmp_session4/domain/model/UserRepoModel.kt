package com.example.kmptraining.kmp_session4.domain.model

data class UserRepoModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val isPrivate: Boolean,

    val htmlUrl: String?,
    val owner: OwnerModel,

    val isFork: Boolean,
    val language: String?,

    val stars: Int,
    val forksCount: Int,
    val watchers: Int,

    val openIssuesCount: Int,

    val topics: List<String>,

    val visibility: String?,
    val defaultBranch: String?,

    val createdAt: String?,
    val updatedAt: String?,
    val pushedAt: String?,

    val permissions: PermissionsModel?
)