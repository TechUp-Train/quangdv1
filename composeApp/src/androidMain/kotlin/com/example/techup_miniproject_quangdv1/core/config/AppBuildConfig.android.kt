package com.example.techup_miniproject_quangdv1.core.config

import com.example.techup_miniproject_quangdv1.BuildConfig

actual object AppBuildConfig {
    actual val API_KEY: String = BuildConfig.API_KEY
    actual val PUBLIC_KEY: String = BuildConfig.PUBLIC_KEY
    actual val BUNDLE_ID: String = BuildConfig.BUNDLE_ID
    actual val APP_NAME: String = BuildConfig.APP_NAME_VALUE
    actual val API_TOKEN: String = BuildConfig.API_TOKEN
    actual val DEVICE_ID: String = BuildConfig.DEVICE_ID
    actual val APP_VERSION: String = BuildConfig.APP_VERSION_VALUE
    actual val BASE_URL: String = BuildConfig.BASE_URL
    actual val TIMESTAMP_BASE_URL: String = BuildConfig.TIMESTAMP_BASE_URL
}
