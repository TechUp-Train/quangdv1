package com.example.techup_miniproject_quangdv1.domain.useCase.getStyles

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.CategoriesItemModel
import kotlinx.coroutines.flow.Flow

interface GetStyleUseCase {
    suspend operator fun invoke(): Flow<ResponseStatus<List<CategoriesItemModel>>>
}