package com.example.techup_miniproject_quangdv1.domain.useCase.downloadImage

import com.example.techup_miniproject_quangdv1.core.utils.FileSaver
import com.example.techup_miniproject_quangdv1.core.utils.ImageDownloader
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlin.time.Clock

class DownloadUseCaseImpl(
    private val downloader: ImageDownloader,
    private val fileSaver: FileSaver
) : DownloadUseCase {
    override suspend fun invoke(url: String): Flow<ResponseStatus<String>> {
        return flow<ResponseStatus<String>> {
            val bytes = downloader.download(url)

            val fileName = "image_${Clock.System.now().epochSeconds}.jpg"

            val path = fileSaver.saveImage(bytes, fileName)

            emit(ResponseStatus.Success(path))
        }.catch { error ->
            emit(ResponseStatus.Error(error.message ?: "Unknown error"))
        }.onStart {
            emit(ResponseStatus.Loading())
        }.flowOn(Dispatchers.IO)
    }
}