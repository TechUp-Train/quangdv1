package com.example.techup_miniproject_quangdv1.core.config

import platform.Foundation.NSBundle

actual object AppBuildConfig {

    private val infoDict: Map<Any?, *>?
        get() = NSBundle.mainBundle.infoDictionary

    private fun getString(key: String, default: String = ""): String {
        return infoDict?.get(key)?.toString() ?: default
    }

    actual val API_KEY: String get() = getString("API_KEY")
    actual val PUBLIC_KEY: String get() = getString("PUBLIC_KEY")
    actual val BUNDLE_ID: String get() = getString("APP_BUNDLE_ID")
    actual val APP_NAME: String get() = getString("APP_NAME_VALUE")
    actual val API_TOKEN: String get() = getString("API_TOKEN")
    actual val DEVICE_ID: String get() = getString("DEVICE_ID")
    actual val APP_VERSION: String get() = getString("APP_VERSION_VALUE")
    actual val BASE_URL: String get() = getString("BASE_URL")
    actual val TIMESTAMP_BASE_URL: String get() = getString("TIMESTAMP_BASE_URL")
}
