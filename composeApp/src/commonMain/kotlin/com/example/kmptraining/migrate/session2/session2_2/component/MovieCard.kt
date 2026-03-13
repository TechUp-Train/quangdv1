package com.example.kmptraining.migrate.session2.session2_2.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.apero.composetraining.common.Movie

@Composable
fun MovieCard(
    movie: Movie,
    onMovieClick: (Movie) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { onMovieClick(movie) },
        colors =
            CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .width(80.dp)
                        .height(120.dp),
                contentScale = ContentScale.Crop,
            )

            Spacer(Modifier.width(3.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    movie.title,
                    style =
                        TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    movie.year.toString(),
                    style =
                        TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.outline,
                        ),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow.copy(alpha = 0.7f),
                )
                Text(
                    movie.rating.toString(),
                    style =
                        TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.outline,
                        ),
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    MovieCard(
        Movie(
            id = 1,
            title = "The Shawshank Redemption",
            year = 1994,
            rating = 9.3f,
            genre = "Drama",
        ),
    ) {}
}
