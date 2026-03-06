package com.example.composetraining.session2.session2_2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apero.composetraining.common.SampleData
import com.example.composetraining.common.bg_page
import com.example.composetraining.common.h
import com.example.composetraining.session2.session2_2.component.AllMoviesSection
import com.example.composetraining.session2.session2_2.component.MovieBrowserAppBar
import com.example.composetraining.session2.session2_2.component.TrendingMoviesSection
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐ BONUS: Movie Browser (tham khảo NewsFeedExercise)
 *
 * Yêu cầu:
 * - LazyRow trên cùng: "Trending" horizontal scroll (poster 120x180dp)
 * - LazyColumn phía dưới: "All Movies" vertical list
 * - Mỗi movie item: Row(Poster + Column(Title + Year + Rating))
 * - Scaffold với TopAppBar "🎬 Movies"
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieBrowserScreen() {
    val movies = SampleData.movies

    Scaffold(
        containerColor = bg_page,
        topBar = {
            // TODO: [Session 2] Bài tập 2 - Tạo TopAppBar với title "🎬 Movies"
            MovieBrowserAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(2.h)
        ) {
            // === TRENDING SECTION ===
            // TODO: [Session 2] Bài tập 2 - Thêm Text "🔥 Trending" (padding 16dp)
            // TODO: [Session 2] Bài tập 2 - Tạo LazyRow hiển thị 8 movies đầu tiên
            // Mỗi item: Card 120x180dp với poster placeholder + title
            // Gợi ý: movies.take(8), horizontalArrangement = Arrangement.spacedBy(12.dp)
            TrendingMoviesSection(movies.take(8)) {}

            // === ALL MOVIES SECTION ===
            // TODO: [Session 2] Bài tập 2 - Thêm Text "📋 All Movies" (padding 16dp)
            // TODO: [Session 2] Bài tập 2 - Tạo LazyColumn hiển thị tất cả movies
            // Mỗi item: Row chứa poster placeholder (80x120dp) + Column(title, year, rating)
            // Gợi ý: items(movies, key = { it.id })
            AllMoviesSection(movies, modifier = Modifier.weight(1f), onMovieClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieBrowserScreenPreview() {
    ComposeTrainingTheme { MovieBrowserScreen() }
}
