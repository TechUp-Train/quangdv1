package com.example.techup_miniproject_quangdv1.domain.useCase.convertImage

import com.example.techup_miniproject_quangdv1.core.utils.ImageByteArrayConverter
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ConvertImageUseCaseImpl(
    private val imageByteArrayConverter: ImageByteArrayConverter
) : ConvertImageUseCase {
    override suspend fun invoke(imageList: List<PlatformImage>): List<ByteArray> = coroutineScope {
        imageList.map { image ->
            async {
                imageByteArrayConverter.convert(image)
            }
        }.awaitAll()
    }
}