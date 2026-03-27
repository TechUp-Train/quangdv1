package com.example.techup_miniproject_quangdv1.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(
    appDeclaration: (KoinApplication.() -> Unit)? = null,
    vararg extraModules: Module,
) {
    startKoin {
        appDeclaration?.invoke(this)

        modules(
            commonModule,
            networkModule,
            repositoryModule,
            useCaseModule,
            viewModelModule,
            *extraModules,
        )
    }
}
