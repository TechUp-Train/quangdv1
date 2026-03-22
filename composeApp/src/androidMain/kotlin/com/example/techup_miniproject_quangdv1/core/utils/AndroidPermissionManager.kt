package com.example.techup_miniproject_quangdv1.core.utils

class AndroidPermissionManager(
    private val requestPermission: suspend () -> Boolean
) : MediaPermissionManager {
    override suspend fun requestGalleryPermission(): Boolean {
        return requestPermission()
    }
}