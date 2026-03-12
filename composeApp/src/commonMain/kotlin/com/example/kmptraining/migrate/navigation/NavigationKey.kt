package com.example.kmptraining.migrate.navigation

sealed class SessionNavigationKey {
    data object Session1 : SessionNavigationKey()
    data object Session2 : SessionNavigationKey()
    data object Session3 : SessionNavigationKey()
    data object Session4 : SessionNavigationKey()
    data object Session5 : SessionNavigationKey()
    data object Session6 : SessionNavigationKey()
}