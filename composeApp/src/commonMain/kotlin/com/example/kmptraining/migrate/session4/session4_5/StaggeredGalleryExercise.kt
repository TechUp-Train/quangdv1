package com.example.kmptraining.migrate.session4.session4_5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session4.session4_5.component.CategoryFilterRow
import com.example.kmptraining.migrate.session4.session4_5.component.StaggeredPhotoGrid
import com.example.kmptraining.migrate.session4.session4_5.data.samplePhotos
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme
import kotlinx.coroutines.delay

/**
 * ⭐⭐⭐⭐⭐ BÀI TẬP NÂNG CAO BUỔI 4: Pinterest-style Staggered Gallery
 *
 * Mô tả: Gallery với chiều cao item khác nhau (staggered) + category filtering
 *
 * ┌─────────────────────────────────────────────────────┐
 * │ 📸 Gallery                                       🔍 │  ← TopAppBar
 * ├─────────────────────────────────────────────────────┤
 * │ [All] [Nature] [City] [People] [Food] [Travel]      │  ← FilterChip LazyRow
 * ├──────────────────────┬──────────────────────────────┤
 * │ ┌──────────────────┐ │ ┌──────────────────────────┐ │
 * │ │  Short photo     │ │ │   Tall photo              │ │  ← Staggered grid
 * │ │  (Nature)        │ │ │   (City)                  │ │
 * │ └──────────────────┘ │ │                           │ │
 * │ ┌──────────────────┐ │ │                           │ │
 * │ │  Very tall photo │ │ └──────────────────────────┘ │
 * │ │  (People)        │ │ ┌──────────────────────────┐ │
 * │ │                  │ │ │  Short photo (Food)       │ │
 * │ └──────────────────┘ │ └──────────────────────────┘ │
 * └──────────────────────┴──────────────────────────────┘
 *
 * Key concepts:
 * - LazyVerticalStaggeredGrid: render grid với các item chiều cao khác nhau
 * - StaggeredGridCells.Adaptive(150.dp): số cột tự động theo width (responsive)
 * - StaggeredGridCells.Fixed(2): luôn 2 cột (không responsive)
 * - PullToRefreshBox: Material3 pull-to-refresh (experimental)
 */

// Categories
private val categories = listOf("All", "Nature", "City", "People", "Food", "Travel")

// ─── Main Screen ──────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaggeredGalleryScreen(modifier: Modifier = Modifier) {
    // 1. State setup:
    //    - var selectedCategory by remember { mutableStateOf("All") }
    //    - var isRefreshing by remember { mutableStateOf(false) }
    //
    // 2. Filter photos với derivedStateOf:
    //    val filteredPhotos by remember(selectedCategory) {
    //        derivedStateOf {
    //            if (selectedCategory == "All") samplePhotos
    //            else samplePhotos.filter { it.category == selectedCategory }
    //        }
    //    }
    //
    // 3. Simulate refresh với LaunchedEffect:
    //    LaunchedEffect(isRefreshing) {
    //        if (isRefreshing) { delay(1500L); isRefreshing = false }
    //    }
    //
    // 4. Scaffold với TopAppBar:
    //    - title: "📸 Gallery"
    //    - actions: IconButton(Search) + IconButton(Refresh, onClick = { isRefreshing = true })
    //
    // 5. Column bên trong:
    //    - CategoryFilterRow(categories, selectedCategory, onCategorySelected)
    //    - Row info: "${filteredPhotos.size} photos" + "Adaptive(150dp)" label
    //    - PullToRefreshBox(isRefreshing, onRefresh = { isRefreshing = true }) {
    //        if (filteredPhotos.isEmpty()) → Empty state
    //        else → StaggeredPhotoGrid(filteredPhotos)
    //      }
    var selectedCategory by rememberSaveable { mutableStateOf(categories.first()) }
    var isRefreshing by remember { mutableStateOf(false) }

    val filteredPhotos by remember(selectedCategory) {
        derivedStateOf {
            if (selectedCategory == "All") samplePhotos
            else samplePhotos.filter { it.category == selectedCategory }
        }
    }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(1500L)
            isRefreshing = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text("📸 Gallery") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Search, contentDescription = "button_search"
                        )
                    }
                    IconButton(
                        onClick = {
                            isRefreshing = true
                            selectedCategory = categories.first()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "button_refresh"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            CategoryFilterRow(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelect = { selectedCategory = it },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${filteredPhotos.size} photos")
                Text("Adaptive(150dp)")

            }
            PullToRefreshBox(
                isRefreshing = isRefreshing, onRefresh = { isRefreshing = true }) {
                if (filteredPhotos.isEmpty()) {
                    Text(
                        text = "No photos found", modifier = Modifier.padding(16.dp)
                    )
                } else {
                    StaggeredPhotoGrid(photos = filteredPhotos)
                }
            }
        }
    }
}

// ─── Previews ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, name = "Staggered Gallery - Light")
@Composable
private fun StaggeredGalleryPreview() {
    ComposeTrainingTheme {
        StaggeredGalleryScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Staggered Gallery - Dark",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun StaggeredGalleryDarkPreview() {
    ComposeTrainingTheme(darkTheme = true) {
        StaggeredGalleryScreen()
    }
}