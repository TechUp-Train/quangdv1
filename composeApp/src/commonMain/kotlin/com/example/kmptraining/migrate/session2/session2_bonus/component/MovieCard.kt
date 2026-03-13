package com.example.kmptraining.migrate.session2.session2_bonus.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.session2.session2_bonus.Movie

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement MovieCard
    // - Box với width(120.dp) + height(160.dp) + clip(RoundedCornerShape(8.dp)) + background(movie.color)
    // - Text emoji (40.sp, align = Center)
    // - Box overlay gradient ở BottomCenter (Transparent → Black.alpha0.8)
    //   + Column bên trong: Text title (labelSmall, Bold, White) + Text "⭐ ${rating}" (labelSmall, White.alpha0.7)
    Box(
        modifier =
            Modifier
                .width(120.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(movie.color),
    ) {
        Text(
            movie.emoji,
            style =
                TextStyle(
                    fontSize = 40.sp,
                ),
            modifier = Modifier.align(Alignment.Center),
        )

        Box(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f),
                            ),
                        ),
                    ),
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                Text(
                    movie.title,
                    style =
                        TextStyle(
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                )
                Text(
                    "⭐ ${movie.rating}",
                    style =
                        TextStyle(
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 10.sp,
                        ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    MovieCard(
        movie =
            Movie(
                title = "Inception",
                genre = "Sci-Fi · Thriller",
                emoji = "🌀",
                rating = "8.8",
                color = Color(0xFF0D1117),
                category = "Movies",
            ),
    )
}
