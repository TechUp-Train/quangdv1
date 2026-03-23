package com.example.techup_miniproject_quangdv1.domain.useCase.processImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class ProcessImageUseCaseImpl(
    private val presignRepository: PresignRepository,
) : ProcessImageUseCase {

    override suspend fun invoke(): Flow<ResponseStatus<PresignLinkModel>> {
        return flow {
            try {
                presignRepository.getTimestamp()
            } catch (e: Exception) {
                emit(ResponseStatus.Error("Error getting timestamp: ${e.message}"))
            }

            val presignLink = presignRepository.getPresignLink()
            if (presignLink != null) {
                emit(ResponseStatus.Success(presignLink))
            } else {
                emit(ResponseStatus.Error("Presign link is null"))
            }
        }.catch { error ->
            emit(ResponseStatus.Error("Error processing image: ${error.message}"))
        }.onStart {
            emit(ResponseStatus.Loading)
        }.flowOn(Dispatchers.IO)
    }
}