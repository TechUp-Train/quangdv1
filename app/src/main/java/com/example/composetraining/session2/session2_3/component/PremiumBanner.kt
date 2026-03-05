package com.example.composetraining.session2.session2_3.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// TODO: [Session 2] Bài tập 3 - Implement PremiumBanner (dùng drawBehind)
// Box(
//     modifier = Modifier
//         .fillMaxWidth()
//         .height(80.dp)
//         .drawBehind {
//             drawRect(brush = Brush.horizontalGradient(...))
//         }
// ) { ... }
@Composable
fun PremiumBanner(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .drawBehind {
                drawRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF6750A4),
                            Color(0xFF9C27B0)
                        )
                    )
                )
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Upgrade to Premium for More Insights!",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PremiumBannerPreview() {
    PremiumBanner()
}