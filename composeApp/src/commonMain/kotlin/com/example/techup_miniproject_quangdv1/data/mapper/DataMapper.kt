package com.example.techup_miniproject_quangdv1.data.mapper

import com.example.techup_miniproject_quangdv1.data.dto.CategoriesItemDto
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageRequestDto
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageResponseDto
import com.example.techup_miniproject_quangdv1.data.dto.PresignLinkDto
import com.example.techup_miniproject_quangdv1.data.dto.StyleRequestDto
import com.example.techup_miniproject_quangdv1.data.dto.StylesItemDto
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel
import com.example.techup_miniproject_quangdv1.domain.model.StyleItemModel
import com.example.techup_miniproject_quangdv1.domain.model.CategoriesItemModel

fun GenerateImageRequest.toDto(): GenerateImageRequestDto = GenerateImageRequestDto(
    files = filePaths,
    mode = mode ?: "IMAGE_EDITING",
    positivePrompt = positivePrompt
)

fun GenerateImageResponseDto.toDomain(): GenerateImageModel = GenerateImageModel(
    url = url,
    path = path
)

fun PresignLinkDto.toDomain(): PresignLinkModel = PresignLinkModel(
    url = url,
    path = path
)

fun StyleRequestDto.toDomain(): List<CategoriesItemModel> = categories?.map { it?.toDomain() ?: CategoriesItemModel() } ?: emptyList()

fun List<CategoriesItemDto>.toDomain(): List<CategoriesItemModel> = map { it.toDomain() }

fun CategoriesItemDto.toDomain(): CategoriesItemModel = CategoriesItemModel(
    categoryName = categoryName ?: "",
    categoryId = categoryId ?: "",
    styles = styles?.map { it?.toDomain() ?: StyleItemModel() } ?: emptyList(),
    categoryThumbnail = categoryThumbnail ?: ""
)

fun StylesItemDto.toDomain(): StyleItemModel = StyleItemModel(
    imagePrompt = imagePrompt ?: "",
    styleTag = styleTag ?: "",
    imageUrl = imageUrl ?: "",
    styleMode = styleMode ?: "",
    styleStatus = styleStatus ?: false,
    imageLimit = imageLimit ?: 0,
    stylePremium = stylePremium ?: false,
    styleId = styleId ?: "",
    styleName = styleName ?: "",
    styleEvent = styleEvent ?: ""
)