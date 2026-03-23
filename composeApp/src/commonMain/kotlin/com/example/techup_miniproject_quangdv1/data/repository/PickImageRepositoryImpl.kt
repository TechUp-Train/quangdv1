package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.core.utils.GalleryImageSource
import com.example.techup_miniproject_quangdv1.core.utils.MediaPermissionManager
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.domain.repository.PickImageRepository

class PickImageRepositoryImpl(
    private val mediaPermissionManager: MediaPermissionManager,
    private val galleryImageSource: GalleryImageSource
) : PickImageRepository {
    override suspend fun requestGalleryPermission(): Boolean {
        return mediaPermissionManager.requestGalleryPermission()
    }

    override suspend fun loadImages(): List<PlatformImage> {
        return galleryImageSource.loadImages()
    }
}