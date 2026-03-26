package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.Photos.PHAsset
import platform.Photos.PHImageContentModeAspectFill
import platform.Photos.PHImageManager
import platform.Photos.PHImageRequestOptions
import platform.Photos.PHImageRequestOptionsDeliveryModeFastFormat
import platform.Photos.PHImageRequestOptionsResizeModeFast
import platform.UIKit.UIImage
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformImageThumbnail(
    image: PlatformImage,
    modifier: Modifier,
    contentScale: ContentScale
) {
    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(image.id) {
        val uiImage = image.asset.loadThumbnail(200.0)

        bitmap = uiImage?.let {
            withContext(Dispatchers.Default) {
                it.toImageBitmapSafe()
            }
        }
    }

    bitmap?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}

@OptIn(ExperimentalForeignApi::class)
suspend fun PHAsset.loadThumbnail(targetSize: Double = 200.0): UIImage? {
    return suspendCancellableCoroutine { continuation ->

        val options = PHImageRequestOptions().apply {
            deliveryMode = PHImageRequestOptionsDeliveryModeFastFormat
            resizeMode = PHImageRequestOptionsResizeModeFast
            synchronous = false
            networkAccessAllowed = true
        }

        PHImageManager.defaultManager().requestImageForAsset(
            asset = this,
            targetSize = platform.CoreGraphics.CGSizeMake(targetSize, targetSize),
            contentMode = PHImageContentModeAspectFill,
            options = options
        ) { image, _ ->
            continuation.resume(image)
        }
    }
}

fun UIImage.toImageBitmapSafe(): ImageBitmap {
    return this.toImageBitmap()
}