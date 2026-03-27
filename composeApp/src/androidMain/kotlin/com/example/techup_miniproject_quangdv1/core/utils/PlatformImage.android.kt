package com.example.techup_miniproject_quangdv1.core.utils

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.Immutable
import java.io.ByteArrayOutputStream

@Immutable
actual class PlatformImage(
    val uri: Uri,
) {
    actual val id: String get() = uri.toString()
}

class AndroidImageByteArrayConverter(
    private val context: Context,
) : ImageByteArrayConverter {
    override suspend fun convert(image: PlatformImage): ByteArray =
        convert(
            image = image,
            maxWidth = 1024,
            maxHeight = 1024,
            quality = 80,
        )

    override suspend fun convert(
        image: PlatformImage,
        maxWidth: Int,
        maxHeight: Int,
        quality: Int,
    ): ByteArray {
        val uri = image.uri
        val contentResolver = context.contentResolver

        val boundsOptions =
            BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
        contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, boundsOptions)
        }

        val originalWidth = boundsOptions.outWidth
        val originalHeight = boundsOptions.outHeight
        if (originalWidth <= 0 || originalHeight <= 0) {
            throw IllegalStateException("Cannot decode image bounds: $uri")
        }

        val megapixels = (originalWidth.toLong() * originalHeight) / 1_000_000
        if (megapixels > 40) {
            throw IllegalArgumentException("Image too large: ${megapixels}MP")
        }

        var sampleSize = 1
        if (originalWidth > maxWidth || originalHeight > maxHeight) {
            val halfWidth = originalWidth / 2
            val halfHeight = originalHeight / 2
            while (halfWidth / sampleSize >= maxWidth && halfHeight / sampleSize >= maxHeight) {
                sampleSize *= 2
            }
        }

        val decodeOptions =
            BitmapFactory.Options().apply {
                inJustDecodeBounds = false
                inSampleSize = sampleSize
            }
        val bitmap =
            tryDecode(contentResolver, image.uri, decodeOptions)
                ?: throw IllegalStateException("Cannot decode image even after fallback: ${image.uri}")

        val toCompress =
            if (bitmap.width > maxWidth || bitmap.height > maxHeight) {
                val scale =
                    minOf(
                        maxWidth.toFloat() / bitmap.width,
                        maxHeight.toFloat() / bitmap.height,
                    )
                val newWidth = (bitmap.width * scale).toInt().coerceAtLeast(1)
                val newHeight = (bitmap.height * scale).toInt().coerceAtLeast(1)
                val scaled = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
                bitmap.recycle()
                scaled
            } else {
                bitmap
            }

        // 5. Compress to JPEG at requested quality
        val outputStream = ByteArrayOutputStream()
        toCompress.compress(Bitmap.CompressFormat.JPEG, quality.coerceIn(1, 100), outputStream)
        val byteArray = outputStream.toByteArray()

        toCompress.recycle()

        return byteArray
    }
}

private fun tryDecode(
    resolver: ContentResolver,
    uri: Uri,
    options: BitmapFactory.Options,
): Bitmap? =
    resolver.openInputStream(uri)?.use {
        BitmapFactory.decodeStream(it, null, options)
    }

actual class ImageByteArrayConverterFactory(
    private val context: Context,
) {
    actual fun create(): ImageByteArrayConverter = AndroidImageByteArrayConverter(context)
}
