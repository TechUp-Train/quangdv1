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
import com.example.techup_miniproject_quangdv1.domain.model.StyleItemModel

@Composable
fun StylesListView(
    categories: List<CategoriesItemModel>,
    selectedCategory: CategoriesItemModel,
    selectedStyle: StyleItemModel?,
    onCategorySelected: (CategoriesItemModel) -> Unit,
    onStyleSelected: (StyleItemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {

        Text(
            "Choose your style",
            style = MaterialTheme.typography.titleLarge.copy(
                color = BrandMagenta
            )
        )

        Spacer(modifier = modifier.height(8.dp))

        CategoriesTabsView(
            categories = categories,
            selectedCategory = selectedCategory,
            onSelected = onCategorySelected
        )

        Spacer(modifier = modifier.height(12.dp))

        StylesRowView(
            styles = categories
                .firstOrNull { cate -> cate.categoryName == selectedCategory.categoryName }
                ?.styles
                .orEmpty(),
            selectedStyle = selectedStyle,
            onStyleSelected = onStyleSelected
        )
    }
}