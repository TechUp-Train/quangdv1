package com.example.composetraining.session5.session5_3.screens.categoryList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.common.h
import com.example.composetraining.session5.session5_3.data.Category

@Composable
fun CategoryItem(category: Category, onClick: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick(category) }
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = Color.LightGray
            )
    ) {
        Box(
            modifier = Modifier
                .background(category.color)
                .height(20.h)
                .fillMaxWidth(),
        )
        Text(
            category.name,
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryItemPreview() {
    CategoryItem(
        category = Category(1, "Category 1", Color.Cyan),
        onClick = {}
    )
}