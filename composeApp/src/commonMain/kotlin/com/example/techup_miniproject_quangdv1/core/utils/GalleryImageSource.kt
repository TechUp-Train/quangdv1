package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.runtime.Composable

interface GalleryImageSource {
    suspend fun loadImages(limit: Int = 5000): List<PlatformImage>
}

@Composable
expect fun rememberGalleryImageSource(): GalleryImageSource