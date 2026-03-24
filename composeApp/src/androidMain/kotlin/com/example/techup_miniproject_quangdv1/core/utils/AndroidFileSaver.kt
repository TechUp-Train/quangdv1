package com.example.techup_miniproject_quangdv1.core.utils

import android.content.Context
import java.io.File

class AndroidFileSaver(
    private val context: Context
) : FileSaver {

    override suspend fun saveImage(
        bytes: ByteArray,
        fileName: String
    ): String {

        val directory = File(context.filesDir, "apero/generatedImages")

        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, fileName)

        file.writeBytes(bytes)

        return file.absolutePath
    }
}