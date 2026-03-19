package com.example.kmptraining.kmp_session5.utils

import androidx.compose.runtime.Composable

interface MediaPermissionManager {
    suspend fun requestGalleryPermission(): Boolean
}

@Composable
expect fun rememberMediaPermissionManager(): MediaPermissionManager