package com.example.kmptraining.kmp_session3.utils

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIGraphicsBeginImageContextWithOptions
import platform.UIKit.UIGraphicsEndImageContext
import platform.UIKit.UIGraphicsGetImageFromCurrentImageContext
import platform.UIKit.UIImage


actual typealias PlatformImage = UIImage

actual class ImageDownsampler {

    @OptIn(ExperimentalForeignApi::class)
    actual fun decodeSampledImage(
        path: String,
        reqWidth: Int,
        reqHeight: Int
    ): UIImage? {

        val image = UIImage.imageWithContentsOfFile(path) ?: return null

        val newSize = platform.CoreGraphics.CGSizeMake(
            reqWidth.toDouble(),
            reqHeight.toDouble()
        )

        UIGraphicsBeginImageContextWithOptions(newSize, false, 1.0)

        image.drawInRect(
            platform.CoreGraphics.CGRectMake(
                0.0,
                0.0,
                reqWidth.toDouble(),
                reqHeight.toDouble()
            )
        )

        val scaledImage = UIGraphicsGetImageFromCurrentImageContext()

        UIGraphicsEndImageContext()

        return scaledImage
    }
}