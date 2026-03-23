package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.core.sinatureSdk.signature.installSignatureInterceptor
import com.example.techup_miniproject_quangdv1.data.dataSource.GenerateDataSource
import com.example.techup_miniproject_quangdv1.data.remote.dataSource.GenerateDataSourceImpl
import com.example.techup_miniproject_quangdv1.data.dataSource.PresignDataSource
import com.example.techup_miniproject_quangdv1.data.remote.dataSource.PresignDataSourceImpl
import com.example.techup_miniproject_quangdv1.data.service.ApiConstants
import com.example.techup_miniproject_quangdv1.data.remote.service.ApiService
import com.example.techup_miniproject_quangdv1.data.remote.service.ApiServiceFactory
import com.example.techup_miniproject_quangdv1.data.service.HttpClientProvider
import com.example.techup_miniproject_quangdv1.data.remote.service.ServiceType
import com.example.techup_miniproject_quangdv1.core.utils.TimestampProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {

    single { TimestampProvider() }

    single(named("base")) {
        HttpClientProvider.provide()
    }

    single(named("signed")) {
        HttpClientProvider.provide {
            installSignatureInterceptor(
                apiKey = ApiConstants.API_KEY,
                publicKey = ApiConstants.PUBLIC_KEY,
                bundleId = ApiConstants.BUNDLE_ID,
                appName = ApiConstants.APP_NAME,
                appVersion = ApiConstants.APP_VERSION,
                countryCode = ApiConstants.COUNTRY_CODE,
                deviceId = ApiConstants.DEVICE_ID,
                timestampProvider = { get<TimestampProvider>().getTimestamp() }
            )
        }
    }

    single {
        ApiServiceFactory(
            baseClient = get(named("base")),
            signedClient = get(named("signed"))
        )
    }

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

    single<GenerateDataSource> {
        GenerateDataSourceImpl(
            uploadService = get(),
            generateService = get()
        )
    }

    single<PresignDataSource> {
        PresignDataSourceImpl(
            timestampService = get(),
            presignService = get()
        )
    }
}
