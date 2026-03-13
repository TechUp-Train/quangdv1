package com.example.kmptraining.migrate.session6.session6_3.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.apero.composetraining.common.ProfileCard
import com.apero.composetraining.common.SampleData
import kotlin.math.abs

// - Card với profile info (name, age, bio)
// - pointerInput { detectDragGestures(...) } cho top card
// - graphicsLayer { rotationZ = offsetX / 20 } cho rotation theo drag
// - Color overlay: red khi swipe left, green khi swipe right
// - Threshold: abs(offsetX) > 300 → dismiss, else snap back
@Composable
fun SwipeableProfileCard(
    profile: ProfileCard,
    isTopCard: Boolean,
    offsetX: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer {
                if (isTopCard) {
                    translationX = offsetX
                    rotationZ = offsetX / 20
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = profile.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (isTopCard && offsetX != 0f) {
                val alpha = (abs(offsetX) / 500f).coerceIn(0f, 0.5f)
                if (offsetX > 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Green.copy(alpha = alpha))
                    )
                } else if (offsetX < 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red.copy(alpha = alpha))
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "${profile.name}, ${profile.age}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
                Text(
                    text = profile.bio,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SwipeableProfileCardPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        SwipeableProfileCard(
            profile = SampleData.profileCards.first(),
            isTopCard = true,
            offsetX = -180f
        )
    }
}
