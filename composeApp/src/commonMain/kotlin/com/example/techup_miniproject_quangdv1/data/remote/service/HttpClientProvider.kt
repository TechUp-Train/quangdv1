package com.example.techup_miniproject_quangdv1.data.service

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Provides a base configuration for Ktor [HttpClient] instances.
 */
object HttpClientProvider {

    fun provide(block: (HttpClientConfig<*>.() -> Unit)? = null): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            level = LogLevel.ALL
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
        }

        block?.invoke(this)
    }
}
