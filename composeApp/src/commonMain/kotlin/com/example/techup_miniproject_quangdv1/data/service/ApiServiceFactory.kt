package com.example.techup_miniproject_quangdv1.data.service

import io.ktor.client.HttpClient

/**
 * Enum representing the types of API services available.
 */
enum class ServiceType {
    TIMESTAMP,
    PRESIGN,
    UPLOAD,
    GENERATE
}

/**
 * Factory class responsible for creating [ApiService] instances.
 *
 * Uses the Factory design pattern to centralize service creation logic.
 * Each service receives the shared [HttpClient] for making network requests.
 *
 * Usage:
 * ```kotlin
 * val factory = ApiServiceFactory(httpClient)
 * val timestampService = factory.create(ServiceType.TIMESTAMP) as ApiService.TimestampService
 * val presignService  = factory.create(ServiceType.PRESIGN) as ApiService.PresignService
 * ```
 *
 * @property client The shared Ktor [HttpClient] instance.
 */
class ApiServiceFactory(private val client: HttpClient) {

    /**
     * Creates an [ApiService] for the given [ServiceType].
     *
     * @param type The type of service to create.
     * @return A concrete [ApiService] implementation.
     */
    fun create(type: ServiceType): ApiService {
        return when (type) {
            ServiceType.TIMESTAMP -> ApiService.TimestampService(client)
            ServiceType.PRESIGN -> ApiService.PresignService(client)
            ServiceType.UPLOAD -> ApiService.UploadService(client)
            ServiceType.GENERATE -> ApiService.GenerateService(client)
        }
    }
}
