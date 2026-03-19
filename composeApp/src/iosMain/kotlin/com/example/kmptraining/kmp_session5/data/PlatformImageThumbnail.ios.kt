package com.example.kmptraining.kmp_session5.data

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.kmptraining.kmp_session5.utils.loadUIImage
import com.example.kmptraining.kmp_session5.utils.toImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIImage

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformImageThumbnail(
    image: PlatformImage,
    modifier: Modifier,
    contentScale: ContentScale
) {
    var uiImage by remember { mutableStateOf<UIImage?>(null) }

    LaunchedEffect(image) {
        uiImage = image.asset.loadUIImage()
    }

    uiImage?.let {
        Image(
            bitmap = it.toImageBitmap(),
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}