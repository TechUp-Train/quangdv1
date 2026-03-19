package com.example.kmptraining.kmp_session5.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberMediaPermissionManager(): MediaPermissionManager {
    return remember { IosMediaPermissionManager() }
}