package com.example.kmptraining.migrate.session5.session5_4.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

enum class Tab {
    FEED, DISCOVER, PROFILE
}

sealed class AuthKey : NavKey {
    @Serializable
    data object Login : AuthKey()
    @Serializable
    data object Register : AuthKey()
    @Serializable
    data object ForgotPassword : AuthKey()
}

sealed class MainKey : NavKey {
    @Serializable
    data object FeedKey : NavKey
    @Serializable
    data class PostDetailKey(val postId: Int) : NavKey
    @Serializable
    data object DiscoverKey : NavKey
    @Serializable
    data object ProfileKey : NavKey
    @Serializable
    data object EditProfileKey : NavKey
}