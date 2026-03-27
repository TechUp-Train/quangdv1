package com.example.techup_miniproject_quangdv1.core.utils

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import java.io.File

class AndroidFileSaver(
    private val context: Context,
) : FileSaver {
    override suspend fun saveImage(
        bytes: ByteArray,
        fileName: String,
    ): String {
        val resolver = context.contentResolver

        val contentValues =
            ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Apero")
            }

        val uri =
            resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                ?: throw IllegalStateException("Failed to create MediaStore entry")

        resolver.openOutputStream(uri)?.use { output ->
            output.write(bytes)
        } ?: throw IllegalStateException("Failed to open output stream")

        return uri.toString()
    }
}
