package com.example.composetraining.session4.session4_5.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

// ─── Category Filter Row ──────────────────────────────────────────────────────
@Composable
fun CategoryFilterRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    // - LazyRow với contentPadding horizontal=16.dp, spacedBy=8.dp
    // - items(categories, key = { it }) { category →
    //     FilterChip(selected = category == selectedCategory, onClick = ...)
    //   }
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories, key = { it }) { category ->
            FilterChip(
                title = category,
                selected = category == selectedCategory,
                onClick = { onCategorySelect(category) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryFilterRowPreview() {
    ComposeTrainingTheme {
        CategoryFilterRow(
            categories = listOf("All", "Nature", "City", "People", "Food", "Travel"),
            selectedCategory = "All",
            onCategorySelect = {},
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}