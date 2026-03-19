package com.example.kmptraining.kmp_session4.domain.model

data class UserModel(
    val id: Int,
    val login: String,
    val name: String,
    val avatarUrl: String?,
    val bio: String?,
    val location: String?,
    val blog: String?,
    val followers: Int,
    val following: Int,
    val publicRepos: Int,
    val htmlUrl: String?
)