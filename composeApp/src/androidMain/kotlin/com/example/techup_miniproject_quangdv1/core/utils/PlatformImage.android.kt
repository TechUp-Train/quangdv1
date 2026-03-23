package com.example.techup_miniproject_quangdv1.core.utils

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
actual class PlatformImage(val uri: Uri) {
    actual val id: String get() = uri.toString()
}