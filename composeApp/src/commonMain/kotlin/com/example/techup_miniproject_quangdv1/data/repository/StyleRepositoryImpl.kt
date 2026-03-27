package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.core.utils.loadStyles
import com.example.techup_miniproject_quangdv1.data.dto.StyleRequestDto
import com.example.techup_miniproject_quangdv1.domain.repository.StyleRepository
import kotlinx.serialization.json.Json

class StyleRepositoryImpl : StyleRepository {
    override suspend fun getStyles(): StyleRequestDto? = loadStyles()
}
