package com.example.kmptraining.kmp_session4.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class AppNavigationKey : NavKey {

    @Serializable
    object Home : AppNavigationKey()

    @Serializable
    object Explore : AppNavigationKey()

    @Serializable
    object Profile : AppNavigationKey()

    @Serializable
    data class RepoDetail(
        val owner: String,
        val repo: String
    ) : AppNavigationKey()
}