package com.example.kmptraining.kmp_session5.data

import android.net.Uri

actual class PlatformImage(val uri: Uri) {
    actual val id: String get() = uri.toString()
}