package com.example.kmptraining.kmp_session2.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavKey : NavKey

object HomeNavigationKey : AppNavKey {
    @Serializable
    data object HomeKey : AppNavKey
    @Serializable
    data class NewsDetailKey(val newsId: Int) : AppNavKey
}

object SettingNavigationKey {
    @Serializable
    data object SettingKey : AppNavKey
    @Serializable
    data object LanguageSettingKey : AppNavKey
}