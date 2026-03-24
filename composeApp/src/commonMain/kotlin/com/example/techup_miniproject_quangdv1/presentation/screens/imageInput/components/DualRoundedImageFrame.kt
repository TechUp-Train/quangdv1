package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techup_miniproject_quangdv1.core.theme.BrandMagenta
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImageThumbnail
import org.jetbrains.compose.resources.painterResource
import techup_miniproject_quangdv1.composeapp.generated.resources.Res
import techup_miniproject_quangdv1.composeapp.generated.resources.ic_change_image
import techup_miniproject_quangdv1.composeapp.generated.resources.img_placeholder

@Composable
fun DualImageSingleBorderFrame(
    image1: PlatformImage? = null,
    image2: PlatformImage? = null,
    onChangeImage1: () -> Unit = {},
    onChangeImage2: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.5.dp,
                color = BrandMagenta,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .matchParentSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            SingleImageContent(
                image = image1,
                onChangeImage = onChangeImage1,
                modifier = Modifier.weight(1f)
            )

            SingleImageContent(
                image = image2,
                onChangeImage = onChangeImage2,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SingleImageContent(
    image: PlatformImage?,
    onChangeImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(12.dp))
    ) {

        if (image == null) {
            Column(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { onChangeImage() },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.img_placeholder),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )

                Text(
                    "Add photo",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black.copy(alpha = 0.4f),
                        fontSize = 14.sp
                    )
                )
            }
        } else {
            PlatformImageThumbnail(
                image = image,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = onChangeImage,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(BrandMagenta.copy(alpha = 0.7f))
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_change_image),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}