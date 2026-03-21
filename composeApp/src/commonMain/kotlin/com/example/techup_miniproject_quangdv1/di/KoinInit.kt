package com.example.techup_miniproject_quangdv1.di

import org.koin.core.context.startKoin

/**
 * Initializes Koin with the application modules.
 * Called from platform-specific entry points (MainActivity on Android, MainViewController on iOS).
 */
fun initKoin() {
    startKoin {
        modules(
            networkModule,
            repositoryModule,
            viewModelModule,
        )
    }
}
