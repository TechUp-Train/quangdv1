package com.example.kmptraining.kmp_session3.utils

expect class PlatformImage

expect class ImageDownsampler {

    fun decodeSampledImage(
        path: String,
        reqWidth: Int,
        reqHeight: Int
    ): PlatformImage?
}