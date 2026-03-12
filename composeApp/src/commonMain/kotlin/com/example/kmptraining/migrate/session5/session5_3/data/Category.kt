package com.example.kmptraining.migrate.session5.session5_3.data

import androidx.compose.ui.graphics.Color

data class Category (
    val id: Int,
    val name: String,
    val color: Color
)

val sampleCategories = listOf(
    Category(1, "Category 1", Color.Cyan),
    Category(2, "Category 2", Color.Yellow),
    Category(3, "Category 3", Color.Red),
)