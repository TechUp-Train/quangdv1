package com.example.composetraining.session2.session2_bonus.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.R
import com.example.composetraining.common.w
import com.example.composetraining.session2.session2_bonus.Movie
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * Hero Banner — Box với gradient text overlay
 * Box(fillMaxWidth, height=280.dp) {
 *     Box(fillMaxSize, background = movie.color) {   ← Background poster
 *         Text emoji (80.sp, Alignment.Center)
 *     }
 *     Box(fillMaxSize, background = Brush.verticalGradient) ← Dark overlay
 *     Column(Alignment.BottomStart, padding=16.dp) { ← Content
 *         Text title + Text genre + Row buttons
 *     }
 * }
 */
@Composable
fun HeroBanner(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    // - Box với fillMaxWidth + height(280.dp)
    // - Lớp 1: Box nền với movie.color + emoji ở giữa
    // - Lớp 2: Box overlay với Brush.verticalGradient (Transparent → Black.alpha0.3 → Black.alpha0.9)
    // - Lớp 3: Column (align = BottomStart, padding = 16.dp):
    //   → Text movie.title (headlineMedium, Bold, White)
    //   → Text "${genre} · ⭐ ${rating}" (bodyMedium, White.alpha0.8)
    //   → Spacer(12.dp)
    //   → Row buttons: Button "▶ Watch" (White bg) + OutlinedButton "+ My List"
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(280.dp),
    ) {
        Box(
            modifier =
                modifier
                    .fillMaxSize()
                    .background(movie.color),
        ) {
            Text(
                text = movie.emoji,
                fontSize = 80.sp,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        Box(
            modifier =
                modifier
                    .fillMaxSize()
                    .background(
                        brush =
                            Brush.verticalGradient(
                                colors =
                                    listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.3f),
                                        Color.Black.copy(alpha = 0.9f),
                                    ),
                            ),
                    ),
        )

        Column(
            modifier =
                modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
        ) {
            Text(
                movie.title,
                style =
                    TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    ),
            )
            Text(
                "${movie.genre} · ⭐ ${movie.rating}",
                style =
                    TextStyle(
                        color = Color.White.copy(alpha = 0.8f),
                    ),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.w),
            ) {
                WatchButton { }
                MyListButton { }
            }
        }
    }
}

@Composable
fun WatchButton(onClick: () -> Unit) {
    Button(
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
            ),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
    ) {
        Text(
            stringResource(R.string.watch),
        )
    }
}

@Composable
fun MyListButton(onClick: () -> Unit) {
    OutlinedButton(
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
            ),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
    ) {
        Text(
            stringResource(R.string.my_list),
        )
    }
}

@Preview(
    showBackground = true,
    name = "Hero Banner Only",
    heightDp = 320,
)
@Composable
private fun HeroBannerPreview() {
    ComposeTrainingTheme {
        HeroBanner(
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
}
