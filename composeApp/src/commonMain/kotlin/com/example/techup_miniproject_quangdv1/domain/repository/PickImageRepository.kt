package com.example.techup_miniproject_quangdv1.domain.repository

import com.example.techup_miniproject_quangdv1.core.utils.GalleryImageSource
import com.example.techup_miniproject_quangdv1.core.utils.MediaPermissionManager
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage

interface PickImageRepository {
    suspend fun requestGalleryPermission(permissionManager: MediaPermissionManager): Boolean
    suspend fun loadImages(galleryImageSource: GalleryImageSource): List<PlatformImage>
}
