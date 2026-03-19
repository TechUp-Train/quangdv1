package com.example.kmptraining.kmp_session5.utils

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.kmptraining.kmp_session5.data.PlatformImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AndroidGalleryImageSource(
    private val context: Context
) : GalleryImageSource {

    override suspend fun loadImages(limit: Int): List<PlatformImage> = withContext(Dispatchers.IO) {
        val images = mutableListOf<PlatformImage>()

        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Images.Media._ID
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while (cursor.moveToNext() && images.size < limit) {
                val id = cursor.getLong(idColumn)
                val contentUri = ContentUris.withAppendedId(collection, id)
                images += PlatformImage(contentUri)
            }
        }

        images
    }
}

@Composable
actual fun rememberGalleryImageSource(): GalleryImageSource {
    val context = LocalContext.current
    return remember(context) { AndroidGalleryImageSource(context) }
}