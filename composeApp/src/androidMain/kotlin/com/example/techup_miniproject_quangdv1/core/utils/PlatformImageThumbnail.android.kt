package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import io.github.suwasto.kmmcomposeshimmer.ShimmerContainer

@Composable
actual fun PlatformImageThumbnail(
    image: PlatformImage,
    modifier: Modifier,
    contentScale: ContentScale
) {
    val context = LocalContext.current

    val model = remember(image.id) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            try {
                context.contentResolver.loadThumbnail(
                    image.uri,
                    android.util.Size(200, 200),
                    null
                )
            } catch (e: Exception) {
                Log.e(Log.UI, "Error loading thumbnail: ${e.message}")
                image.uri
            }
        } else {
            image.uri
        }
    }

    val request = remember(model) {
        ImageRequest.Builder(context)
            .data(model)
            .size(200, 200)
            .crossfade(true)
            .memoryCacheKey(image.id)
            .build()
    }

    var isLoading by remember(image.id) { mutableStateOf(true) }

    Box(
        modifier = modifier.clip(RoundedCornerShape(15.dp))
    ) {
        ShimmerContainer(
            isLoading = isLoading,
            modifier = Modifier.matchParentSize()
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
        }

        AsyncImage(
            model = request,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = contentScale,
            onLoading = { isLoading = true },
            onSuccess = { isLoading = false },
            onError = { isLoading = false }
        )
    }
}