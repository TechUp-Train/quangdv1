package com.example.techup_miniproject_quangdv1.core.config

/**
 * expect declaration for platform-specific build configuration.
 * On Android, reads from the generated BuildConfig.
 * On iOS, reads from hardcoded/bundled values.
 */
expect object AppBuildConfig {
    val API_KEY: String
    val PUBLIC_KEY: String
    val BUNDLE_ID: String
    val APP_NAME: String
    val API_TOKEN: String
    val DEVICE_ID: String
    val APP_VERSION: String
    val BASE_URL: String
    val TIMESTAMP_BASE_URL: String
}
