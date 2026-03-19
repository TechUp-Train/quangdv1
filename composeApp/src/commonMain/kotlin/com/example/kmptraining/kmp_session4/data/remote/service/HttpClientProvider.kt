package com.example.kmptraining.kmp_session4.data.remote.service

import io.ktor.client.HttpClient

expect fun provideHttpClient(
    tokenProvider: () -> String?
): HttpClient