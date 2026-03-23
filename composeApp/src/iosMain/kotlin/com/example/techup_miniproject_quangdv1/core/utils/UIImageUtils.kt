package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.posix.memcpy
import kotlinx.cinterop.*

fun UIImage.toImageBitmap(): ImageBitmap {
    val jpegData: NSData = UIImageJPEGRepresentation(this, 1.0)
        ?: error("Unable to convert UIImage to NSData")

    val byteArray = jpegData.toByteArray()

    return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}

@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray {
    val byteArray = ByteArray(length.toInt())

    byteArray.usePinned { pinned ->
        memcpy(
            pinned.addressOf(0),
            bytes,
            length
        )
    }

    return byteArray
}