package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Photos.PHAsset
import platform.Photos.PHImageManager
import platform.Photos.PHImageRequestOptions
import platform.Photos.PHImageRequestOptionsDeliveryModeHighQualityFormat
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Immutable
actual class PlatformImage(val asset: PHAsset) {
    actual val id: String get() = asset.localIdentifier
}

class IOSImageByteArrayConverter : ImageByteArrayConverter {

    override suspend fun convert(image: PlatformImage): ByteArray {
        return suspendCancellableCoroutine { continuation ->

            val options = PHImageRequestOptions().apply {
                synchronous = false
                deliveryMode = PHImageRequestOptionsDeliveryModeHighQualityFormat
            }

            PHImageManager.defaultManager()
                .requestImageDataAndOrientationForAsset(
                    image.asset,
                    options
                ) { data, _, _, _ ->

                    if (data != null) {
                        continuation.resume(data.toByteArray())
                    } else {
                        continuation.resumeWithException(
                            IllegalStateException("Failed to get image data")
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
