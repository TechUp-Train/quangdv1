package com.example.kmptraining.kmp_session5.data

import platform.Foundation.NSURL

actual fun ImageModel.getUriString(): String? {
    return when (val handle = platformHandle) {
        is NSURL -> handle.absoluteString
        is String -> handle
        else -> null
    }
}