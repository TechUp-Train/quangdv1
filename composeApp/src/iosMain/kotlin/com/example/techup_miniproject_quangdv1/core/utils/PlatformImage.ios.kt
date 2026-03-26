package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.runtime.Immutable
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreGraphics.CGSizeMake
import platform.Photos.PHAsset
import platform.Photos.PHImageContentModeAspectFit
import platform.Photos.PHImageManager
import platform.Photos.PHImageRequestOptions
import platform.Photos.PHImageRequestOptionsDeliveryModeHighQualityFormat
import platform.UIKit.UIImageJPEGRepresentation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Immutable
actual class PlatformImage(val asset: PHAsset) {
    actual val id: String get() = asset.localIdentifier
}

class IOSImageByteArrayConverter : ImageByteArrayConverter {

    override suspend fun convert(image: PlatformImage): ByteArray {
        return convert(image, 1024, 1024, 80)
    }

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun convert(
        image: PlatformImage,
        maxWidth: Int,
        maxHeight: Int,
        quality: Int
    ): ByteArray {
        return suspendCancellableCoroutine { continuation ->
            val options = PHImageRequestOptions().apply {
                synchronous = false
                deliveryMode = PHImageRequestOptionsDeliveryModeHighQualityFormat
            }

            val targetSize = CGSizeMake(maxWidth.toDouble(), maxHeight.toDouble())

            PHImageManager.defaultManager().requestImageForAsset(
                image.asset,
                targetSize,
                PHImageContentModeAspectFit,   // preserves aspect ratio, fits inside max size
                options
            ) { uiImage, _ ->
                if (uiImage != null) {
                    val jpegData = UIImageJPEGRepresentation(
                        uiImage,
                        quality.toDouble() / 100.0
                    )
                    if (jpegData != null) {
                        continuation.resume(jpegData.toByteArray())
                    } else {
                        continuation.resumeWithException(
                            IllegalStateException("Failed to compress image to JPEG")
                        )
                    }
                } else {
                    continuation.resumeWithException(
                        IllegalStateException("Failed to get image from PHAsset")
                    )
                }
            }
        }
    }
}

actual class ImageByteArrayConverterFactory {
    actual fun create(): ImageByteArrayConverter {
        return IOSImageByteArrayConverter()
    }
}
