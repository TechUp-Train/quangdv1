package com.example.kmptraining.kmp_session4.data.remote.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun provideHttpClient(tokenProvider: () -> String?): HttpClient {
    return createHttpClient(
        engine = Darwin.create(),
        tokenProvider = tokenProvider
    )
}