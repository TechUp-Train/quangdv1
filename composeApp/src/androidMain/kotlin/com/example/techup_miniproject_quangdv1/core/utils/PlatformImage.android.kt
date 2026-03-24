package com.example.techup_miniproject_quangdv1.core.utils

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
actual class PlatformImage(val uri: Uri) {
    actual val id: String get() = uri.toString()
}

class AndroidImageByteArrayConverter(
    private val context: Context
) : ImageByteArrayConverter {

    override suspend fun convert(image: PlatformImage): ByteArray {
        return context.contentResolver
            .openInputStream(image.uri)
            ?.use { it.readBytes() }
            ?: throw IllegalStateException("Cannot read image from URI: ${image.uri}")
    }
}

actual class ImageByteArrayConverterFactory(
    private val context: Context
) {
    actual fun create(): ImageByteArrayConverter {
        return AndroidImageByteArrayConverter(context)
    }
}