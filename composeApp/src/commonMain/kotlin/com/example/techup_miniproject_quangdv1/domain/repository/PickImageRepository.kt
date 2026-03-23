package com.example.techup_miniproject_quangdv1.domain.repository

import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage

interface PickImageRepository {
    suspend fun requestGalleryPermission(): Boolean
    suspend fun loadImages(): List<PlatformImage>
}