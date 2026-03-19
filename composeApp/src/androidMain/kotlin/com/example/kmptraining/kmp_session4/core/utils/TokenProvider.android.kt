package com.example.kmptraining.kmp_session4.core.utils

import com.example.kmptraining.BuildConfig

actual object TokenProvider {
    actual fun getToken(): String = BuildConfig.GITHUB_TOKEN
}