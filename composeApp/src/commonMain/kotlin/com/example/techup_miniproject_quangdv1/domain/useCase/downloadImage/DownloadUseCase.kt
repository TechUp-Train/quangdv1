package com.example.techup_miniproject_quangdv1.domain.useCase.downloadImage

import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface DownloadUseCase {
    suspend operator fun invoke(url: String): Flow<ResponseStatus<String>>
}
