package com.example.kmptraining.kmp_session5

import kotlinx.cinterop.*
import platform.Foundation.NSData
import platform.posix.memcpy

@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray {
    val byteArray = ByteArray(length.toInt())
    memScoped {
        val buffer = byteArray.refTo(0)
        memcpy(buffer, bytes, length)
    }
    return byteArray
}