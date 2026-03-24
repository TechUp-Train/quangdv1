package com.example.techup_miniproject_quangdv1.domain.useCase.generateImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import com.example.techup_miniproject_quangdv1.domain.repository.GenerateRepository
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

class GenerateImageUseCaseImpl(
    private val presignRepository: PresignRepository,
    private val generateRepository: GenerateRepository,
) : GenerateImageUseCase {

    override suspend fun invoke(request: GenerateImageRequest): Flow<ResponseStatus<GenerateImageModel>> {
        return flow {
            if (request.imageBytes.isEmpty()) emit(ResponseStatus.Error("Image data is empty."))

            presignRepository.getTimestamp()

            try {
                coroutineScope {
                    request.imageBytes.mapIndexed { index, byteArray ->
                        async {
                            val uploaded = generateRepository.uploadImage(
                                presignUrl = request.uploadUrls[index],
                                imageBytes = byteArray
                            )
                            if (!uploaded) throw Exception("Image upload failed at index $index.")
                        }
                    }.awaitAll()
                }
            } catch (e: Exception) {
                emit(ResponseStatus.Error("Image upload failed: ${e.message}"))
                return@flow
            }

            val generateResponse = generateRepository.generateImage(request)
            if (generateResponse == null) {
                emit(ResponseStatus.Error("Image generation failed."))
            } else {
                emit(ResponseStatus.Success(generateResponse))
            }
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(e.message ?: "Unknown error"))
        }.flowOn(Dispatchers.IO)
    }
}
