package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.runtime.Composable

interface MediaPermissionManager {
    suspend fun requestGalleryPermission(): Boolean
}

@Composable
expect fun rememberMediaPermissionManager(): MediaPermissionManager