package com.example.kmptraining.migrate.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class SessionNavigationKey : NavKey {
    @Serializable
    data object SessionList : SessionNavigationKey()

    @Serializable
    data class
    Session(val sessionName: String) : SessionNavigationKey()
    @Serializable
    data class SubSession(val subSessionName: String) : SessionNavigationKey()
}