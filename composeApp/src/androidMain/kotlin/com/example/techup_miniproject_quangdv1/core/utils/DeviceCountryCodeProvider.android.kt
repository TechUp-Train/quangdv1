package com.example.techup_miniproject_quangdv1.core.utils

import java.util.Locale

/**
 * Android actual implementation.
 * Reads the device's country code from [Locale.getDefault].
 *
 * @return ISO 3166-1 alpha-2 country code (e.g. "VN", "US").
 */
actual object DeviceCountryCodeProvider {
    actual fun getCountryCode(): String {
        return Locale.getDefault().country
    }
}
