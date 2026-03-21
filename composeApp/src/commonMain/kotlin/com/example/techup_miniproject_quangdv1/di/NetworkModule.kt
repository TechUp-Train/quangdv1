package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.data.service.ApiService
import com.example.techup_miniproject_quangdv1.data.service.ApiServiceFactory
import com.example.techup_miniproject_quangdv1.data.service.HttpClientProvider
import com.example.techup_miniproject_quangdv1.data.service.ServiceType
import org.koin.dsl.module

/**
 * Koin module providing network-layer dependencies.
 *
 * Provides:
 * - [HttpClient] singleton via [HttpClientProvider]
 * - [ApiServiceFactory] singleton for creating API services
 * - Individual service instances created through the factory
 */
val networkModule = module {

    // ── HttpClient (singleton) ─────────────────────────────────
    single { HttpClientProvider.provide() }

    // ── ApiServiceFactory (singleton) ──────────────────────────
    single { ApiServiceFactory(client = get()) }

    // ── Individual API Services (via factory) ──────────────────
    single {
        get<ApiServiceFactory>().create(ServiceType.TIMESTAMP) as ApiService.TimestampService
    }

    single {
        get<ApiServiceFactory>().create(ServiceType.PRESIGN) as ApiService.PresignService
    }

    single {
        get<ApiServiceFactory>().create(ServiceType.UPLOAD) as ApiService.UploadService
    }

    single {
        get<ApiServiceFactory>().create(ServiceType.GENERATE) as ApiService.GenerateService
    }
}
