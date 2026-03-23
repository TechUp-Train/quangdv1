package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techup_miniproject_quangdv1.core.theme.BrandMagenta
import com.example.techup_miniproject_quangdv1.domain.model.CategoriesItemModel

@Composable
fun StylesListView(
    categories: List<CategoriesItemModel>,
    selectedCategoryIndex: Int,
    selectedStyleId: String?,
    onCategorySelected: (Int) -> Unit,
    onStyleSelected: (String) -> Unit
) {
    Column {

        Text(
            "Choose your style",
            style = MaterialTheme.typography.titleLarge.copy(
                color = BrandMagenta
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        CategoriesTabsView(
            categories = categories,
            selectedIndex = selectedCategoryIndex,
            onSelected = onCategorySelected
        )

        Spacer(modifier = Modifier.height(12.dp))

        StylesRowView(
            styles = categories
                .getOrNull(selectedCategoryIndex)
                ?.styles
                .orEmpty(),
            selectedStyleId = selectedStyleId,
            onStyleSelected = onStyleSelected
        )
    }
}