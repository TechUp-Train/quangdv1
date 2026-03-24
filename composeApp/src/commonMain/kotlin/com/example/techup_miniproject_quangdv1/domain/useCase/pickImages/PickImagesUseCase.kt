package com.example.techup_miniproject_quangdv1.domain.useCase.pickImages

import com.example.techup_miniproject_quangdv1.core.utils.GalleryImageSource
import com.example.techup_miniproject_quangdv1.core.utils.MediaPermissionManager
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface PickImagesUseCase {
    suspend operator fun invoke(
        permissionManager: MediaPermissionManager,
        galleryImageSource: GalleryImageSource
    ): Flow<ResponseStatus<List<PlatformImage>>>
}
