package com.example.kmptraining.kmp_session5.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
expect fun PlatformImageThumbnail(
    image: PlatformImage,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
)