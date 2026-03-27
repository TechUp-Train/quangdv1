package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techup_miniproject_quangdv1.core.theme.BrandMagenta
import com.example.techup_miniproject_quangdv1.domain.model.CategoriesItemModel

@Composable
fun CategoriesTabsView(
    categories: List<CategoriesItemModel>,
    selectedCategory: CategoriesItemModel,
    onSelected: (CategoriesItemModel) -> Unit,
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(categories) { _, item ->
            val isSelected = item.categoryName == selectedCategory.categoryName

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onSelected(item) },
            ) {
                Text(
                    text = item.categoryName ?: "",
                    color = if (isSelected) BrandMagenta else Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.height(4.dp))

                if (isSelected) {
                    Box(
                        modifier =
                            Modifier
                                .height(2.dp)
                                .width(24.dp)
                                .background(BrandMagenta),
                    )
                }
            }
        }
    }
}
