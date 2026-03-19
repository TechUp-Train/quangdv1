package com.example.kmptraining.kmp_session5.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
actual fun PlatformImageThumbnail(
    image: PlatformImage,
    modifier: Modifier,
    contentScale: ContentScale
) {
    AsyncImage(
        model = image.uri,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )
}