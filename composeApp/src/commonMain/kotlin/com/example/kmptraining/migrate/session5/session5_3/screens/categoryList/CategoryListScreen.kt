package com.example.kmptraining.migrate.session5.session5_3.screens.categoryList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session5.session5_3.data.Category
import com.example.kmptraining.migrate.session5.session5_3.data.sampleCategories

@Composable
fun CategoryListScreen(
    categories: List<Category>,
    onCategoryClick: (Int) -> Unit,
    cartCount: Int
) {
    LazyVerticalGrid (
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(categories.size) { index ->
            CategoryItem(
                category = categories[index],
                onClick = { onCategoryClick(it.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryListPreview() {
    CategoryListScreen(
        categories = sampleCategories,
        onCategoryClick = {},
        cartCount = 0

    )
}