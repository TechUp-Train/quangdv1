package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberMediaPermissionManager(): MediaPermissionManager {
    return remember { IosMediaPermissionManager() }
}