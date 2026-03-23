package com.example.techup_miniproject_quangdv1.domain.repository

import com.example.techup_miniproject_quangdv1.data.dto.StyleRequestDto

interface StyleRepository {
    suspend fun getStyles(): StyleRequestDto?
}