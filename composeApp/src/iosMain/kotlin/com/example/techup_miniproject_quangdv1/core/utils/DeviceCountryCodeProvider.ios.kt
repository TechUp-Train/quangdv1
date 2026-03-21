package com.example.techup_miniproject_quangdv1.core.utils

import platform.Foundation.NSLocale
import platform.Foundation.countryCode
import platform.Foundation.currentLocale

/**
 * iOS actual implementation.
 * Reads the device's country code from [NSLocale.currentLocale].
 *
 * @return ISO 3166-1 alpha-2 country code (e.g. "VN", "US").
 */
actual object DeviceCountryCodeProvider {
    actual fun getCountryCode(): String {
        return NSLocale.currentLocale.countryCode ?: "US"
    }
}
