package com.example.kmptraining.kmp_session4.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            sharedModule,
            viewModelModule,
            platformModule
        )
    }
}