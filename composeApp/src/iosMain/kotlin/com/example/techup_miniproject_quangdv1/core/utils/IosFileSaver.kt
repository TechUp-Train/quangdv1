package com.example.techup_miniproject_quangdv1.core.utils

import io.github.vinceglb.filekit.utils.toNSData
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*
import platform.Photos.PHAssetChangeRequest
import platform.Photos.PHPhotoLibrary
import platform.UIKit.UIImage

class IOSFileSaver : FileSaver {
    @OptIn(ExperimentalForeignApi::class)
    override suspend fun saveImage(
        bytes: ByteArray,
        fileName: String,
    ): String {
        val nsData = bytes.toNSData()

        val image =
            UIImage.imageWithData(nsData)
                ?: throw IllegalStateException("Failed to create UIImage")

        PHPhotoLibrary.sharedPhotoLibrary().performChanges({
            PHAssetChangeRequest.creationRequestForAssetFromImage(image)
        }, null)

        return "Saved to Photos"
    }
}
