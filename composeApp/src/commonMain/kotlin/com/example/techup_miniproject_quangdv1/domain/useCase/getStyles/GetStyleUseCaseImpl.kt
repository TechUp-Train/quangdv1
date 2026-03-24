package com.example.techup_miniproject_quangdv1.domain.useCase.getStyles

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.data.mapper.toDomain
import com.example.techup_miniproject_quangdv1.domain.model.CategoriesItemModel
import com.example.techup_miniproject_quangdv1.domain.repository.StyleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class GetStyleUseCaseImpl(
    private val styleRepository: StyleRepository
) : GetStyleUseCase {
    override suspend fun invoke(): Flow<ResponseStatus<List<CategoriesItemModel>>> {
        return flow {
            val styles = styleRepository.getStyles()
            if (styles != null) {
                emit(ResponseStatus.Success(styles.toDomain()))
            } else {
                emit(ResponseStatus.Error(message = "Error getting styles"))
            }
        }.catch { error ->
            emit(ResponseStatus.Error(message = "Error getting styles: ${error.message}"))
        }.onStart {
            emit(ResponseStatus.Loading())
        }.flowOn(Dispatchers.IO)
    }
}