package com.example.kmptraining.migrate.session2.session2_bonus.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChipRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    // - Row với horizontalScroll(rememberScrollState()) + padding(horizontal=16.dp)
    // - horizontalArrangement = spacedBy(8.dp)
    // - Với mỗi category: FilterChip(selected, onClick, label)
    // GỢI Ý: Row(horizontalScroll) nhẹ hơn LazyRow cho list ngắn (<10 items)
    val scrollState = rememberScrollState()

    Row(
        modifier =
            modifier
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        categories.forEachIndexed { index, category ->
            FilterChip(
                category = category,
                selected = category == selectedCategory,
                onClick = { onCategorySelect(category) },
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D0D)
@Composable
private fun CategoryChipRowPreview() {
    CategoryChipRow(
        categories = listOf("All", "Movies", "Series", "Anime", "Documentary", "Kids"),
        selectedCategory = "All",
        onCategorySelect = {},
    )
}
