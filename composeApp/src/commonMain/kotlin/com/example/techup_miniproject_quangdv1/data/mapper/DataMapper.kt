package com.example.techup_miniproject_quangdv1.data.mapper

import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageRequestDto
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageResponseDto
import com.example.techup_miniproject_quangdv1.data.dto.PresignLinkDto
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel

/**
 * Mappers for converting between Data Transfer Objects (DTOs) and Domain Models.
 */

fun GenerateImageRequest.toDto(): GenerateImageRequestDto = GenerateImageRequestDto(
    files = files,
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
