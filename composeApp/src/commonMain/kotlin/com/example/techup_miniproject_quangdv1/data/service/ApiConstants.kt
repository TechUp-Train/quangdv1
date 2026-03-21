package com.example.techup_miniproject_quangdv1.data.service

import com.example.techup_miniproject_quangdv1.core.config.AppBuildConfig
import com.example.techup_miniproject_quangdv1.core.utils.DeviceCountryCodeProvider

/**
 * Central place for all API-related constants.
 *
 * Sensitive values (API keys, URLs, etc.) are read from [AppBuildConfig],
 * which sources them from local.properties → BuildConfig on Android
 * and from bundled values on iOS.
 *
 * Country code is fetched at runtime from the device's native locale via [DeviceCountryCodeProvider].
 *
 * Only non-sensitive structural constants (header key names, endpoint paths) are hardcoded here.
 */
object ApiConstants {

    // ── Base URLs (from local.properties) ──────────────────────
    val BASE_URL: String get() = AppBuildConfig.BASE_URL
    val TIMESTAMP_BASE_URL: String get() = AppBuildConfig.TIMESTAMP_BASE_URL

    // ── Endpoints ──────────────────────────────────────────────
    const val TIMESTAMP_ENDPOINT = "/timestamp"
    const val PRESIGN_LINK_ENDPOINT = "/api/v5.1/qwen-editing/presigned-link"
    const val GENERATE_ENDPOINT = "/api/v5.1/qwen-editing"

    // ── Auth / Header Keys ─────────────────────────────────────
    const val HEADER_BUNDLE_ID = "x-api-bundleId"
    const val HEADER_SIGNATURE = "x-api-signature"
    const val HEADER_TIMESTAMP = "x-api-timestamp"
    const val HEADER_TOKEN = "x-api-token"
    const val HEADER_DEVICE_ID = "x-api-deviceid"
    const val HEADER_APP_NAME = "app-name"
    const val HEADER_COUNTRY_CODE = "country-code"
    const val HEADER_APP_VERSION = "app-version"

    // ── Default Header Values (from local.properties) ──────────
    val API_KEY: String get() = AppBuildConfig.API_KEY
    val PUBLIC_KEY: String get() = AppBuildConfig.PUBLIC_KEY
    val BUNDLE_ID: String get() = AppBuildConfig.BUNDLE_ID
    val APP_NAME: String get() = AppBuildConfig.APP_NAME
    val TOKEN: String get() = AppBuildConfig.API_TOKEN
    val DEVICE_ID: String get() = AppBuildConfig.DEVICE_ID
    val APP_VERSION: String get() = AppBuildConfig.APP_VERSION

    // ── Device Country Code (from native APIs at runtime) ──────
    val COUNTRY_CODE: String get() = DeviceCountryCodeProvider.getCountryCode()
}
