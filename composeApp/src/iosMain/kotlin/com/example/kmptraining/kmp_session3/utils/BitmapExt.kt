package com.example.kmptraining.kmp_session3.utils

import coil3.Bitmap
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIImage

@OptIn(ExperimentalForeignApi::class)
fun Bitmap.toUIImage(): UIImage {
    return this as UIImage
}

fun UIImage.toBitmap(): Bitmap {
    return this as Bitmap
}