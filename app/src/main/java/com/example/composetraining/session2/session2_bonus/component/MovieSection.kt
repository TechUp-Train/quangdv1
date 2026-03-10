package com.example.composetraining.session2.session2_bonus.component

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.common.h
import com.example.composetraining.common.w
import com.example.composetraining.session2.session2_bonus.Movie
import com.example.composetraining.session2.session2_bonus.MovieSection

@Composable
fun MovieSectionView(
    section: MovieSection,
    onSecTionClick: (MovieSection) -> Unit,
    modifier: Modifier = Modifier
) {
    // - Column:
    //   → Text section.title (titleMedium, Bold, White, padding horizontal=16dp vertical=8dp)
    //   → Row với horizontalScroll(rememberScrollState()) + padding(horizontal=16.dp) + spacedBy(12.dp)
    //   → Mỗi movie: MovieCard(movie)
    // GỢI Ý: Tại sao KHÔNG dùng LazyRow ở đây?
    // → Nested Lazy với cùng hướng scroll → crash. Khác hướng thì OK.
    val scrollState = rememberScrollState()
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(1.h)
    ) {
        Row(
            modifier = modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple(color = Color.White.copy(alpha = 0.4f)),
                    onClick = { onSecTionClick(section) }
                ),
            horizontalArrangement = Arrangement.spacedBy(1.w)
        ) {
            Text(
                section.title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            section.movies.forEachIndexed { index, movie ->
                MovieCard(movie)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D0D)
@Composable
private fun MovieSectionViewPreview() {
    MovieSectionView(
        section = MovieSection(
            title = "🔥 Trending Now",
            movies = listOf(
                Movie("The Matrix", "Sci-Fi", "💊", "8.7", "Movies", Color(0xFF0D2818)),
                Movie("Dune", "Epic", "🏜️", "8.0", "Movies", Color(0xFF2D1B00)),
                Movie("Interstellar", "Space", "🌌", "8.6", "Movies", Color(0xFF000D1A)),
                Movie("Blade Runner", "Neo-noir", "🤖", "8.1", "Movies", Color(0xFF1A0000)),
                Movie("Tenet", "Action", "⏰", "7.4", "Movies", Color(0xFF001A2D))
            )
        ),
        onSecTionClick = {}
    )
}