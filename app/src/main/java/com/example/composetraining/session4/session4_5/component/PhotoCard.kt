package com.example.composetraining.session4.session4_5.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.session4.session4_5.data.Photo
import com.example.composetraining.session4.session4_5.data.samplePhotos
import com.example.composetraining.ui.theme.ComposeTrainingTheme

// ─── Photo Card ───────────────────────────────────────────────────────────────
@Composable
fun PhotoCard(
    photo: Photo,
    height: Dp,
    modifier: Modifier = Modifier,
) {
    // - Box với fillMaxWidth + height(height) + clip(shapes.medium)
    // - Lớp 1: Box nền với background(photo.color)
    // - Lớp 2: Box gradient overlay ở BottomCenter (height=80.dp)
    //   background = Brush.verticalGradient(Transparent → Black.alpha0.7)
    // - Lớp 3: Column (align = BottomStart, padding=8.dp):
    //   → Text title (12.sp, Medium, White, maxLines=2)
    //   → Spacer(4.dp)
    //   → Surface chip với category text (10.sp, White, White.alpha0.25 background)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(photo.color)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Text(
                text = photo.title,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.small,
                color = Color.White.copy(alpha = 0.25f)
            ) {
                Text(
                    text = photo.category,
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Photo Card Preview")
@Composable
private fun PhotoCardPreview() {
    ComposeTrainingTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
            PhotoCard(photo = samplePhotos[0], height = 180.dp, modifier = Modifier.weight(1f))
            PhotoCard(photo = samplePhotos[1], height = 240.dp, modifier = Modifier.weight(1f))
        }
    }
}