package com.example.kmptraining.kmp_session5.data

import platform.Photos.PHAsset

actual class PlatformImage(val asset: PHAsset) {
    actual val id: String get() = asset.localIdentifier
}