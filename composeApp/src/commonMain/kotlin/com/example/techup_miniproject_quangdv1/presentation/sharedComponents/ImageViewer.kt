package com.example.techup_miniproject_quangdv1.presentation.sharedComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import com.example.techup_miniproject_quangdv1.core.theme.BrandMagenta
import org.jetbrains.compose.resources.painterResource
import techup_miniproject_quangdv1.composeapp.generated.resources.Res
import techup_miniproject_quangdv1.composeapp.generated.resources.ic_change_image
import techup_miniproject_quangdv1.composeapp.generated.resources.img_placeholder

@Composable
fun RoundedImageFrame(
    imagePainter: Painter? = null,
    onChangeImage: () -> Unit = {},
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

        if (imagePainter == null) {
            Column(
                modifier = Modifier.matchParentSize()
                    .clickable { onChangeImage() },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.img_placeholder),
                    contentDescription = "Placeholder",
                    modifier = Modifier.size(48.dp)
                )

                Text(
                    "Add your photo",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            Image(
                painter = imagePainter,
                contentDescription = "Selected Image",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = onChangeImage,
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(BrandMagenta.copy(alpha = 0.7f))
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_change_image),
                    contentDescription = "Change image",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoundedImageFramePreview() {
    RoundedImageFrame(
        imagePainter = painterResource(Res.drawable.img_placeholder),
        onChangeImage = {},
        modifier = Modifier
    )
}