package com.example.kmptraining.kmp_session5.utils

class AndroidPermissionManager(
    private val requestPermission: suspend () -> Boolean
) : MediaPermissionManager {
    override suspend fun requestGalleryPermission(): Boolean {
        return requestPermission()
    }
}

