package com.example.techup_miniproject_quangdv1.data.service

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Provides a configured Ktor [HttpClient] singleton for the application.
 *
 * Plugins installed:
 * - [ContentNegotiation] with kotlinx.serialization JSON (lenient, ignores unknown keys)
 * - [Logging] at INFO level for debugging
 * - Default request configuration with JSON content type
 */
object HttpClientProvider {

    fun provide(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.INFO
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
        }
    }
}
