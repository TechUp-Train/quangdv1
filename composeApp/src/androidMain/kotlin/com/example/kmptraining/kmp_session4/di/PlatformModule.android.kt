package com.example.kmptraining.kmp_session4.di

import com.example.kmptraining.kmp_session4.core.utils.TokenProvider
import com.example.kmptraining.kmp_session4.data.local.database.createDatabase
import com.example.kmptraining.kmp_session4.data.local.database.getDatabaseBuilder
import com.example.kmptraining.kmp_session4.data.remote.service.provideHttpClient
import org.koin.dsl.module

actual val platformModule = module {

    single {
        provideHttpClient(
            tokenProvider = { TokenProvider.getToken() }
        )
    }

    single {
        val builder = getDatabaseBuilder(get())
        createDatabase(builder)
    }
}