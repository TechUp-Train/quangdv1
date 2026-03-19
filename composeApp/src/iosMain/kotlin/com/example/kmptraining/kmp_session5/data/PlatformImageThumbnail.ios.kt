package com.example.kmptraining.kmp_session5.data

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.example.kmptraining.kmp_session5.utils.loadUIImage
import com.example.kmptraining.kmp_session5.utils.toImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformImageThumbnail(
    image: PlatformImage,
    modifier: Modifier,
    contentScale: ContentScale
) {
    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(image) {
        val uiImage = image.asset.loadUIImage()
        bitmap = uiImage?.let {
            withContext(Dispatchers.IO) {
                it.toImageBitmap()
            }
        }
    }

    bitmap?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}