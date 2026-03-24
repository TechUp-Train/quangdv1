package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.core.utils.GalleryImageSource
import com.example.techup_miniproject_quangdv1.core.utils.MediaPermissionManager
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.domain.repository.PickImageRepository

class PickImageRepositoryImpl : PickImageRepository {
    override suspend fun requestGalleryPermission(permissionManager: MediaPermissionManager): Boolean {
        return permissionManager.requestGalleryPermission()
    }

    override suspend fun loadImages(galleryImageSource: GalleryImageSource): List<PlatformImage> {
        return galleryImageSource.loadImages()
    }
}
