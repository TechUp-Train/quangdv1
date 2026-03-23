package com.example.techup_miniproject_quangdv1.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
expect fun PlatformImageThumbnail(
    image: PlatformImage,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
)