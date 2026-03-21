package com.example.techup_miniproject_quangdv1.core.utils

/**
 * Provides the device's country code using platform-specific native APIs.
 *
 * - Android: Uses [java.util.Locale.getDefault().country]
 * - iOS: Uses [NSLocale.currentLocale.countryCode]
 *
 * Returns an ISO 3166-1 alpha-2 code (e.g. "VN", "US", "JP").
 */
expect object DeviceCountryCodeProvider {
    fun getCountryCode(): String
}
