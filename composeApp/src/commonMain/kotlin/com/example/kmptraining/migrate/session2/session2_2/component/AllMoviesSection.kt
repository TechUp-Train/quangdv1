package com.example.kmptraining.migrate.session2.session2_2.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apero.composetraining.common.Movie
import com.apero.composetraining.common.SampleData
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.all_movies
import org.jetbrains.compose.resources.stringResource

@Composable
fun AllMoviesSection(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
    ) {
        Text(
            stringResource(Res.string.all_movies),
            modifier = Modifier.padding(16.dp),
            style =
                TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                ),
        )

        LazyColumn(
            modifier =
                Modifier
                    .padding(top = 5.dp)
                    .fillMaxSize(),
        ) {
            itemsIndexed(
                items = movies,
                key = { _, movie -> movie.id },
            ) { index, movie ->
                MovieCard(movie, onMovieClick)
                if (index < movies.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        thickness = 1.dp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllMoviesSectionPreview() {
    AllMoviesSection(SampleData.movies, {}, Modifier)
}
