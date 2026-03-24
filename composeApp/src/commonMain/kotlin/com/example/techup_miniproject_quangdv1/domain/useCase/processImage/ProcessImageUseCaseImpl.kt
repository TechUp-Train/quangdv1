package com.example.techup_miniproject_quangdv1.domain.useCase.processImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class ProcessImageUseCaseImpl(
    private val presignRepository: PresignRepository,
) : ProcessImageUseCase {

    override suspend fun invoke(imageCount: Int): Flow<ResponseStatus<List<PresignLinkModel>>> {
        return flow {
            try {
                presignRepository.getTimestamp()
            } catch (e: Exception) {
                // Ignore timestamp error for now if it's non-critical
            }

            val presignLinks = coroutineScope {
                (0 until imageCount).map {
                    async { presignRepository.getPresignLink() }
                }.awaitAll()
            }

            if (presignLinks.all { it != null }) {
                emit(ResponseStatus.Success(presignLinks.filterNotNull()))
            } else {
                emit(ResponseStatus.Error("One or more presigned links are null"))
            }
        }.catch { error ->
            emit(ResponseStatus.Error("Error processing image: ${error.message}"))
        }.onStart {
            emit(ResponseStatus.Loading())
        }.flowOn(Dispatchers.IO)
    }
}
