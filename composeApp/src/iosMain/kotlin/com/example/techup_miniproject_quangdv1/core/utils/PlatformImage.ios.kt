package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.runtime.Immutable
import platform.Photos.PHAsset

@Immutable
actual class PlatformImage(val asset: PHAsset) {
    actual val id: String get() = asset.localIdentifier
}