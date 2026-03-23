package com.example.techup_miniproject_quangdv1.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StyleRequestDto(
	val categories: List<CategoriesItemDto?>? = null
)

@Serializable
data class CategoriesItemDto(
	@SerialName("category_name")
	val categoryName: String? = null,

	@SerialName("category_id")
	val categoryId: String? = null,

	val styles: List<StylesItemDto?>? = null,

	@SerialName("category_thumbnail")
	val categoryThumbnail: String? = null
)

@Serializable
data class StylesItemDto(
	@SerialName("image_prompt")
	val imagePrompt: String? = null,

	@SerialName("style_tag")
	val styleTag: String? = null,

	@SerialName("image_url")
	val imageUrl: String? = null,

	@SerialName("style_mode")
	val styleMode: String? = null,

	@SerialName("style_status")
	val styleStatus: Boolean? = null,

	@SerialName("image_limit")
	val imageLimit: Int? = null,

	@SerialName("style_premium")
	val stylePremium: Boolean? = null,

	@SerialName("style_id")
	val styleId: String? = null,

	@SerialName("style_name")
	val styleName: String? = null,

	@SerialName("style_event")
	val styleEvent: String? = null
)