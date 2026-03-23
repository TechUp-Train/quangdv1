package com.example.techup_miniproject_quangdv1.domain.useCase.pickImages

import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.domain.repository.PickImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class PickImagesUseCaseImpl(
    private val pickImageRepository: PickImageRepository
) : PickImagesUseCase {
    override suspend fun invoke(): Flow<ResponseStatus<List<PlatformImage>>> {
        return flow {
            val permissionGranted = pickImageRepository.requestGalleryPermission()

            if (permissionGranted) {
                val images = pickImageRepository.loadImages()
                emit(ResponseStatus.Success(images))
            } else {
                emit(ResponseStatus.Error("Permission denied"))
            }
        }.catch { error ->
            emit(ResponseStatus.Error("Error loading images: ${error.message}"))
        }.onStart {
            emit(ResponseStatus.Loading)
        }.flowOn(Dispatchers.IO)
    }
}