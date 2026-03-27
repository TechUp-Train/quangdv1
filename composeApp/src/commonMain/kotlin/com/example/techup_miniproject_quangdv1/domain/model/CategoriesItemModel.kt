package com.example.techup_miniproject_quangdv1.domain.model

data class CategoriesItemModel(
    val categoryName: String = "",
    val categoryId: String = "",
    val styles: List<StyleItemModel> = emptyList(),
    val categoryThumbnail: String = "",
)

data class StyleItemModel(
    val imagePrompt: String = "",
    val styleTag: String = "",
    val imageUrl: String = "",
    val styleMode: String = "",
    val styleStatus: Boolean = false,
    val imageLimit: Int = 0,
    val stylePremium: Boolean = false,
    val styleId: String = "",
    val styleName: String = "",
    val styleEvent: String = "",
)
