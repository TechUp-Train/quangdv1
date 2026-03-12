package com.example.kmptraining.migrate.session1.session1_4.data

import androidx.compose.runtime.Composable

data class SocialPostData (
    val username: String,
    val timeAgo: String,
    val content: String,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    val retweetCount: Int = 0,
    val attachment: (@Composable () -> Unit)? = null,
)