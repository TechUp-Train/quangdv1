package com.example.techup_miniproject_quangdv1.core.utils

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Photos.PHAuthorizationStatusAuthorized
import platform.Photos.PHAuthorizationStatusLimited
import platform.Photos.PHPhotoLibrary
import kotlin.coroutines.resume

class IosMediaPermissionManager : MediaPermissionManager {

    override suspend fun requestGalleryPermission(): Boolean {
        val status = PHPhotoLibrary.authorizationStatus()

        if (status == PHAuthorizationStatusAuthorized ||
            status == PHAuthorizationStatusLimited
        ) return true

        return suspendCancellableCoroutine { cont ->
            PHPhotoLibrary.requestAuthorization { newStatus ->
                cont.resume(
                    newStatus == PHAuthorizationStatusAuthorized ||
                            newStatus == PHAuthorizationStatusLimited
                )
            }
        }
    }
}