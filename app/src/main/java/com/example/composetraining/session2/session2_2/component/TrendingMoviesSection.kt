package com.example.composetraining.session2.session2_2.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.apero.composetraining.common.Movie
import com.apero.composetraining.common.SampleData
import com.example.composetraining.R

@Composable
fun TrendingMoviesSection(movies: List<Movie>, onMovieClick: (Movie) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(5.dp)
    ) {
        Text(
            stringResource(R.string.trending),
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
        ) {
            items(movies.size, key = { movies[it].id }) { index ->
                AsyncImage(
                    model = movies[index].posterUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .width(120.dp)
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = { onMovieClick(movies[index]) }),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrendingMoviesSectionPreview() {
    TrendingMoviesSection(SampleData.movies, {})
}
