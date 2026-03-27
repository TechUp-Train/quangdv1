package com.example.techup_miniproject_quangdv1.core.utils

import platform.Foundation.NSLocale
import platform.Foundation.countryCode
import platform.Foundation.currentLocale

actual object DeviceCountryCodeProvider {
    actual fun getCountryCode(): String = NSLocale.currentLocale.countryCode ?: "US"
}
