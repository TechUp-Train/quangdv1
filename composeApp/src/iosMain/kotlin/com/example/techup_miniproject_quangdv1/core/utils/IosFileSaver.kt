package com.example.techup_miniproject_quangdv1.core.utils

import io.github.vinceglb.filekit.utils.toNSData
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*

class IOSFileSaver : FileSaver {

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun saveImage(
        bytes: ByteArray,
        fileName: String
    ): String {

        val fileManager = NSFileManager.defaultManager

        val documentsDir = fileManager.URLsForDirectory(
            directory = NSDocumentDirectory,
            inDomains = NSUserDomainMask
        ).firstOrNull() as? NSURL
            ?: throw IllegalStateException("Cannot access documents directory")

        val imagesDir = documentsDir
            .URLByAppendingPathComponent("apero/generateImages")
            ?: throw IllegalStateException("Cannot create images directory URL")

        fileManager.createDirectoryAtURL(
            url = imagesDir,
            withIntermediateDirectories = true,
            attributes = null,
            error = null
        )

        val fileURL = imagesDir
            .URLByAppendingPathComponent(fileName)
            ?: throw IllegalStateException("Cannot create file URL")

        val data = bytes.toNSData()

        val success = data.writeToURL(fileURL, atomically = true)

        if (!success) {
            throw IllegalStateException("Failed to write file")
        }

        return fileURL.path ?: throw IllegalStateException("Invalid file path")
    }
}