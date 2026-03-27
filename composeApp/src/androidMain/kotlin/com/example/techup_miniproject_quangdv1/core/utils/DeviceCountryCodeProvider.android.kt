package com.example.techup_miniproject_quangdv1.core.utils

import java.util.Locale

actual object DeviceCountryCodeProvider {
    actual fun getCountryCode(): String = Locale.getDefault().country
}
