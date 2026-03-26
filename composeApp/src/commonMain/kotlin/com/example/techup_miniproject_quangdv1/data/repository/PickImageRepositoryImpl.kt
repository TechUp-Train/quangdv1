package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.core.utils.GalleryImageSource
import com.example.techup_miniproject_quangdv1.core.utils.Log
import com.example.techup_miniproject_quangdv1.core.utils.MediaPermissionManager
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.domain.repository.PickImageRepository

class PickImageRepositoryImpl : PickImageRepository {
    override suspend fun requestGalleryPermission(permissionManager: MediaPermissionManager): Boolean {
        return try {
            permissionManager.requestGalleryPermission()
        } catch (e: Exception) {
            Log.e(Log.REPOSITORY, "Error requesting permission: ${e.message}")
            false
        }
    }

    override suspend fun loadImages(galleryImageSource: GalleryImageSource): List<PlatformImage> {
        return try {
            galleryImageSource.loadImages()
        } catch (e: Exception) {
            Log.e(Log.REPOSITORY, "Error loading images from gallery: ${e.message}")
            emptyList()
        }
    }
}
