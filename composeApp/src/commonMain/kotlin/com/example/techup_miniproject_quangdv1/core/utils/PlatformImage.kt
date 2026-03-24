package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.runtime.Immutable

@Immutable
expect class PlatformImage {
    val id: String
}

expect class ImageByteArrayConverterFactory {
    fun create(): ImageByteArrayConverter
}

interface ImageByteArrayConverter {
    suspend fun convert(image: PlatformImage): ByteArray
}