package com.example.techup_miniproject_quangdv1.core.utils

interface FileSaver {
    suspend fun saveImage(
        bytes: ByteArray,
        fileName: String,
    ): String
}
