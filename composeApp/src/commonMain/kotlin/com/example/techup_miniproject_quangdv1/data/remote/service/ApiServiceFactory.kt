package com.example.techup_miniproject_quangdv1.data.remote.service

import io.ktor.client.HttpClient

enum class ServiceType {
    TIMESTAMP,
    PRESIGN,
    UPLOAD,
    GENERATE,
}

class ApiServiceFactory(
    private val baseClient: HttpClient,
    private val signedClient: HttpClient,
) {
    fun create(type: ServiceType): ApiService =
        when (type) {
            ServiceType.TIMESTAMP -> ApiService.TimestampService(signedClient)
            ServiceType.PRESIGN -> ApiService.PresignService(signedClient)
            ServiceType.UPLOAD -> ApiService.UploadService(baseClient)
            ServiceType.GENERATE -> ApiService.GenerateService(signedClient)
        }
}
