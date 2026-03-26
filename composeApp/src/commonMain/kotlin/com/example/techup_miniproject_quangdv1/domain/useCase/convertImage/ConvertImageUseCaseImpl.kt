package com.example.techup_miniproject_quangdv1.domain.useCase.convertImage
import com.example.techup_miniproject_quangdv1.core.utils.ImageByteArrayConverter
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ConvertImageUseCaseImpl(
    private val imageByteArrayConverter: ImageByteArrayConverter
) : ConvertImageUseCase {
    override suspend fun invoke(imageList: List<PlatformImage>): ResponseStatus<List<ByteArray>> =
        try {
            coroutineScope {
                val results = imageList.map { image ->
                    async {
                        imageByteArrayConverter.convert(image)
                    }
                }.awaitAll()
                ResponseStatus.Success(results)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            ResponseStatus.Error(e.message ?: "Unknown error")
        }
}