package com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.techup_miniproject_quangdv1.core.utils.ImageMode
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.core.utils.rememberGalleryImageSource
import com.example.techup_miniproject_quangdv1.core.utils.rememberMediaPermissionManager
import com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery.components.GalleryAppBar
import com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery.components.GalleryErrorUI
import com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery.components.GalleryItem
import com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery.components.GalleryLoadingUI
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GalleryScreen(
    imageMode: ImageMode,
    onSelectImages: (List<PlatformImage>) -> Unit,
    onBack: () -> Unit,
    viewModel: GalleryViewModel = koinViewModel()
) {
    val imagesState = viewModel.imagesState.collectAsStateWithLifecycle().value
    val maxSelection = imageMode.requiredImageCount

    val permissionManager = rememberMediaPermissionManager()
    val gallerySource = rememberGalleryImageSource()

    var selectedIds by remember { mutableStateOf(emptySet<String>()) }

    LaunchedEffect(Unit) {
        viewModel.loadImages(permissionManager, gallerySource)
    }

    Scaffold(
        topBar = {
            GalleryAppBar(
                onBack = onBack,
                onConfirm = {
                    if (imagesState is ResponseStatus.Success) {
                        val selectedImages = imagesState.data.filter { selectedIds.contains(it.id) }
                        onSelectImages(selectedImages)
                    }
                },
                selectedCount = selectedIds.size,
                maxSelections = maxSelection,
            )
        }
    ) { innerPadding ->
        when (imagesState) {
            is ResponseStatus.Idle -> {}
            is ResponseStatus.Loading -> {
                GalleryLoadingUI(modifier = Modifier.padding(innerPadding))
            }

            is ResponseStatus.Error -> {
                GalleryErrorUI(
                    message = imagesState.message,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is ResponseStatus.Success -> {
                val images = imagesState.data

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = innerPadding,
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(
                        images,
                        key = { _, image -> image.id },
                        contentType = { _, _ -> "gallery_image" }
                    ) { _, image ->
                        val isSelected = selectedIds.contains(image.id)

                        GalleryItem(
                            image = image,
                            isSelected = isSelected,
                            onToggle = {
                                selectedIds = if (isSelected) {
                                    selectedIds - image.id
                                } else {
                                    if (selectedIds.size < maxSelection) {
                                        selectedIds + image.id
                                    } else {
                                        selectedIds
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}