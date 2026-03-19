package com.example.kmptraining.kmp_session4.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class HomeNavigationKey : NavKey {
    @Serializable
    object Home : HomeNavigationKey()

    @Serializable
    class RepoDetail(val owner: String, val repo: String) : HomeNavigationKey()
}

sealed class SearchNavigationKey : NavKey {
    @Serializable
    object Search : SearchNavigationKey()
    @Serializable
    class RepoDetail(val owner: String, val repo: String) : SearchNavigationKey()
}

sealed class ProfileNavigationKey : NavKey {
    @Serializable
    object Profile : ProfileNavigationKey()
    @Serializable
    class RepoDetail(val owner: String, val repo: String) : ProfileNavigationKey()
}