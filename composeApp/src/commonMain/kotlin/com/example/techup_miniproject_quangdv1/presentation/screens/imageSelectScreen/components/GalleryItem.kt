package com.example.techup_miniproject_quangdv1.presentation.screens.imageSelectScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImageThumbnail
import org.jetbrains.compose.resources.painterResource
import techup_miniproject_quangdv1.composeapp.generated.resources.Res
import techup_miniproject_quangdv1.composeapp.generated.resources.ic_select_image
import techup_miniproject_quangdv1.composeapp.generated.resources.ic_selected_image

@Composable
fun GalleryItem(
    image: PlatformImage,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(onClick = onToggle)
    ) {
        PlatformImageThumbnail(
            image = image,
            modifier = Modifier.fillMaxSize()
        )

        Icon(
            painter = painterResource(if (isSelected) Res.drawable.ic_selected_image else Res.drawable.ic_select_image),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}