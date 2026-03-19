package com.example.kmptraining.kmp_session5.utils

import androidx.compose.runtime.Composable
import com.example.kmptraining.kmp_session5.data.PlatformImage

interface GalleryImageSource {
    suspend fun loadImages(limit: Int = 30): List<PlatformImage>
}

@Composable
expect fun rememberGalleryImageSource(): GalleryImageSource