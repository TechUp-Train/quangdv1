package com.example.kmptraining.kmp_session4.data.remote.service

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import co.touchlab.kermit.Logger as Logger

fun HttpClientConfig<out HttpClientEngineConfig>.setupErrorInterceptor() {
    HttpResponseValidator {
        validateResponse { response ->
            when (val statusCode = response.status.value) {
                in 400..499 -> throw Exception("Client Error: $statusCode")
                in 500..599 -> throw Exception("Server Error: $statusCode")
            }
        }
        handleResponseExceptionWithRequest { cause, request ->
            Logger.e { "Network Exception: ${cause.message}" }
        }
    }
}

fun createHttpClient(
    engine: HttpClientEngine,
    tokenProvider: () -> String?
): HttpClient {
    return HttpClient(engine) {
        defaultRequest {
            url("https://api.github.com/")
            header("Accept", "application/vnd.github+json")
            tokenProvider()?.takeIf { it.isNotBlank() }?.let {
                header("Authorization", "Bearer $it")
            }
        }

        install(Logging) {
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        setupErrorInterceptor()
    }
}