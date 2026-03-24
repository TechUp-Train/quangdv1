package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Size

@Composable
actual fun PlatformImageThumbnail(
    image: PlatformImage,
    modifier: Modifier,
    contentScale: ContentScale
) {
    val context = LocalContext.current
    val request = remember(image.id) {
        ImageRequest.Builder(context)
            .data(image.uri)
            .size(Size(200, 200))
            .crossfade(true)
            .build()
    }

    val errorPainter = rememberVectorPainter(Icons.Default.Error)

    AsyncImage(
        model = request,
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(15.dp)),
        contentScale = contentScale,
        error = errorPainter,
    )
}