package com.example.kmptraining.kmp_session5.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.kmptraining.kmp_session5.data.PlatformImage
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.CoreGraphics.CGSize
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSSortDescriptor
import platform.Photos.PHAsset
import platform.Photos.PHAssetMediaTypeImage
import platform.Photos.PHFetchOptions
import platform.Photos.PHFetchResult
import platform.Photos.PHImageContentModeAspectFill
import platform.Photos.PHImageManager
import platform.Photos.PHImageRequestOptions
import platform.Photos.PHImageRequestOptionsDeliveryModeHighQualityFormat
import platform.Photos.PHImageRequestOptionsResizeModeFast
import platform.UIKit.UIImage
import kotlin.coroutines.resume

class IosGalleryImageSource : GalleryImageSource {

    override suspend fun loadImages(limit: Int): List<PlatformImage> {
        return withContext(Dispatchers.Main) {

            val result = mutableListOf<PlatformImage>()
            val options = PHFetchOptions().apply {
                sortDescriptors = listOf(
                    NSSortDescriptor(
                        key = "creationDate",
                        ascending = false
                    )
                )
            }

            val fetchResult: PHFetchResult =
                PHAsset.fetchAssetsWithMediaType(
                    mediaType = PHAssetMediaTypeImage,
                    options = options
                )

            val count = minOf(fetchResult.count.toInt(), limit)

            for (i in 0 until count) {
                val asset = fetchResult.objectAtIndex(i.toULong()) as? PHAsset ?: continue
                result.add(PlatformImage(asset))
            }

            result
        }
    }
}

@Composable
actual fun rememberGalleryImageSource(): GalleryImageSource {
    return remember { IosGalleryImageSource() }
}

@OptIn(ExperimentalForeignApi::class)
suspend fun PHAsset.loadUIImage(
    targetSize: CValue<CGSize> = CGSizeMake(300.0, 300.0)
): UIImage? = suspendCancellableCoroutine { cont ->

    val options = PHImageRequestOptions().apply {
        deliveryMode = PHImageRequestOptionsDeliveryModeHighQualityFormat
        resizeMode = PHImageRequestOptionsResizeModeFast
    }

    PHImageManager.defaultManager().requestImageForAsset(
        asset = this,
        targetSize = targetSize,
        contentMode = PHImageContentModeAspectFill,
        options = options
    ) { image, _ ->
        cont.resume(image)
    }
}