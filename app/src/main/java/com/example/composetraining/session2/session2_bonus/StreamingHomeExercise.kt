package com.example.composetraining.session2.session2_bonus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.session2.session2_bonus.component.CategoryChipRow
import com.example.composetraining.session2.session2_bonus.component.HeroBanner
import com.example.composetraining.session2.session2_bonus.component.MovieSectionView
import com.example.composetraining.session2.session2_bonus.component.StreamingHomeAppBar
import com.example.composetraining.ui.theme.ComposeTrainingTheme

// /**
// * ⭐⭐⭐⭐⭐ BONUS NÂNG CAO: Streaming App Home (Nested Scroll — Concept 5) (Netflix-style)
// *
// * Mô tả: Build Home screen với nested scrolling đúng cách
// *
// * ┌──────────────────────────────────┐
// * │  🎬 StreamApp                🔍  │  ← TopBar
// * │                                  │
// * │  ┌────────────────────────────┐  │  ← Hero Banner (Box overlay)
// * │  │      FEATURED MOVIE        │  │
// * │  │      Inception             │  │
// * │  │      [▶ Watch] [+ List]   │  │
// * │  └────────────────────────────┘  │
// * │                                  │
// * │  Trending Now                    │  ← Section title
// * │  [  ] [  ] [  ] [  ] →         │  ← Horizontal scroll
// * │                                  │
// * │  Continue Watching               │
// * │  [  ] [  ] [  ] →              │
// * └──────────────────────────────────┘  ↕ Vertical scroll (outer)
// *
// * Yêu cầu — NESTED SCROLLING ĐÚNG CÁCH:
// * ❌ SAI: Column(verticalScroll) + nhiều LazyColumn/LazyRow
// * ✅ ĐÚNG: Column(verticalScroll) + Row(horizontalScroll) bên trong
// *   Hoặc: LazyColumn với item { Row(horizontalScroll) }
// *
// * Yêu cầu kỹ thuật:
// * 1. Hero Banner: Box với overlay gradient (background dark gradient overlay)
// * 2. Category chips: Row với horizontalScroll (nhẹ hơn LazyRow cho < 10 items)
// * 3. Movie row: Row(horizontalScroll) — không phải LazyRow (để tránh nested Lazy crash)
// * 4. Outer scroll: Column(verticalScroll) — chứa tất cả sections
// * 5. Arrangement.spacedBy cho movie row
// * 6. contentPadding bằng padding trên Column, không phải từng item
// *
// * Khái niệm Buổi 2:
// * - Nested scroll giải pháp: Column(verticalScroll) + Row(horizontalScroll)
// * - Box + overlay = gradient trên image
// * - Arrangement.spacedBy vs Arrangement.SpaceBetween
// * - Modifier.horizontalScroll() vs LazyRow
// */

// ─── Data Models ──────────────────────────────────────────────────────────────

data class Movie(
    val title: String,
    val genre: String,
    val emoji: String,
    val rating: String,
    val category: String,
    val color: Color = Color(0xFF1E1E2E),
)

data class MovieSection(
    val title: String,
    val movies: List<Movie>,
)

// ─── Sample Data ──────────────────────────────────────────────────────────────

private val featuredMovie =
    Movie(
        title = "Inception",
        genre = "Sci-Fi · Thriller",
        emoji = "🌀",
        rating = "8.8",
        category = "Movies",
        color = Color(0xFF0D1117),
    )

private val movieSections =
    listOf(
        MovieSection(
            title = "🔥 Trending Now",
            movies =
                listOf(
                    Movie("The Matrix", "Sci-Fi", "💊", "8.7", "Movies", Color(0xFF0D2818)),
                    Movie("Dune", "Epic", "🏜️", "8.0", "Movies", Color(0xFF2D1B00)),
                    Movie("Interstellar", "Space", "🌌", "8.6", "Movies", Color(0xFF000D1A)),
                    Movie("Blade Runner", "Neo-noir", "🤖", "8.1", "Movies", Color(0xFF1A0000)),
                    Movie("Tenet", "Action", "⏰", "7.4", "Movies", Color(0xFF001A2D)),
                ),
        ),
        MovieSection(
            title = "▶ Continue Watching",
            movies =
                listOf(
                    Movie("Pulp Fiction", "Crime", "🎬", "8.9", "Movies", Color(0xFF1A0D00)),
                    Movie("Dark Knight", "Action", "🦇", "9.0", "Movies", Color(0xFF0D0D0D)),
                    Movie("Parasite", "Thriller", "🏠", "8.5", "Movies", Color(0xFF0D1A0D)),
                ),
        ),
        MovieSection(
            title = "🎭 Because you watched Inception",
            movies =
                listOf(
                    Movie("Shutter Island", "Mystery", "🏝️", "8.1", "Movies", Color(0xFF0D1A2D)),
                    Movie("Prestige", "Drama", "🎩", "8.5", "Movies", Color(0xFF1A1A0D)),
                    Movie("Memento", "Thriller", "📸", "8.4", "Movies", Color(0xFF2D0D0D)),
                    Movie("Fight Club", "Drama", "🥊", "8.8", "Movies", Color(0xFF1A0D1A)),
                ),
        ),
        MovieSection(
            title = "📺 Popular Series",
            movies =
                listOf(
                    Movie("Breaking Bad", "Crime", "⚗️", "9.5", "Series", Color(0xFF0D1A0D)),
                    Movie("Stranger Things", "Sci-Fi", "🚲", "8.7", "Series", Color(0xFF1A0000)),
                    Movie("The Crown", "History", "👑", "8.6", "Series", Color(0xFF1A1A0D)),
                ),
        ),
    )

private val categories = listOf("All", "Movies", "Series", "Anime", "Documentary", "Kids")

// ─── Main Screen ──────────────────────────────────────────────────────────────

/**
 * Streaming Home Screen
 * Column(                                     ← Outer vertical scroll
 *     modifier = Modifier.verticalScroll(...)
 * ) {
 *     HeroBanner(...)                         ← Box với gradient overlay
 *     CategoryChipRow(...)                    ← Row với horizontalScroll
 *
 *     movieSections.forEach { section ->
 *         SectionTitle(...)
 *         MovieRow(section.movies)            ← Row với horizontalScroll (KHÔNG phải LazyRow!)
 *     }
 * }
 *
 * ⚠️ WARNING: Đừng dùng LazyRow bên trong Column(verticalScroll)!
 * → LazyRow cần unbounded width constraint
 * → Column(verticalScroll) gives unbounded height
 * → Nested unbounded = CRASH
 *
 * Dùng Row(horizontalScroll) thay thế cho danh sách nhỏ (< 20 items)
 */
@Composable
fun StreamingHomeScreen(modifier: Modifier = Modifier) {
    // - rememberScrollState() cho outer scroll
    // - var selectedCategory by remember { mutableStateOf("All") }
    // - Column với fillMaxSize + verticalScroll(scrollState) + background(Color(0xFF0D0D0D))
    //   → HeroBanner(featuredMovie)
    //   → Spacer(16.dp)
    //   → CategoryChipRow(categories, selectedCategory, onCategorySelect)
    //   → Spacer(24.dp)
    //   → movieSections.forEach { section → MovieSection(section) + Spacer(24.dp) }
    //   → Spacer(80.dp) ← space cho FAB
    val scrollState = rememberScrollState()
    var selectedCategory by remember { mutableStateOf("All") }

    val filteredMovies by remember {
        derivedStateOf {
            if (selectedCategory == "All") {
                movieSections
            } else {
                movieSections
                    .map { section ->
                        section.copy(
                            movies =
                                section.movies.filter { movie ->
                                    movie.category == selectedCategory
                                },
                        )
                    }.filter { it.movies.isNotEmpty() }
            }
        }
    }

    Scaffold(
        topBar = { StreamingHomeAppBar {} },
    ) { innerPadding ->
        Column(
            modifier =
                modifier
                    .fillMaxSize()
                    .background(Color(0xFF0D0D0D))
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
        ) {
            HeroBanner(featuredMovie)
            Spacer(modifier = Modifier.height(16.dp))
            CategoryChipRow(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelect = { selectedCategory = it },
            )
            Spacer(modifier = Modifier.height(24.dp))
            filteredMovies.forEachIndexed { index, section ->
                MovieSectionView(section = section, onSecTionClick = {})
                if (index < movieSections.size) {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

// ─── Preview ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, name = "Streaming Home — Dark")
@Composable
private fun StreamingHomePreview() {
    ComposeTrainingTheme {
        StreamingHomeScreen()
    }
}

// ─── Câu Hỏi Thảo Luận ───────────────────────────────────────────────────────
/*
 * Q1: Tại sao Row(horizontalScroll) thay vì LazyRow bên trong Column(verticalScroll)?
 * Q2: Khi nào dùng Row(horizontalScroll) vs LazyRow?
 * Q3: Gradient overlay trên HeroBanner — tại sao cần 2 Box thay vì 1?
 * Q4: contentPadding vs padding — khác gì?
 */
