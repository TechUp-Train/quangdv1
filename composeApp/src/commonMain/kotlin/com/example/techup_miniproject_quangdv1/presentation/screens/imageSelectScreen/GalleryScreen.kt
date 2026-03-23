package com.example.techup_miniproject_quangdv1.presentation.screens.imageSelectScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.techup_miniproject_quangdv1.presentation.screens.imageSelectScreen.components.GalleryAppBar
import com.example.techup_miniproject_quangdv1.presentation.screens.imageSelectScreen.components.GalleryErrorUI
import com.example.techup_miniproject_quangdv1.presentation.screens.imageSelectScreen.components.GalleryItem
import com.example.techup_miniproject_quangdv1.presentation.screens.imageSelectScreen.components.GalleryLoadingUI
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GalleryScreen(
    imageMode: ImageMode,
    onSelectImages: (List<PlatformImage>) -> Unit,
    viewModel: GalleryViewModel = koinViewModel()
) {
    val imagesState = viewModel.imagesState.collectAsStateWithLifecycle().value
    val maxSelection = imageMode.requiredImageCount

    var selected by remember { mutableStateOf(emptyList<String>()) }


    Scaffold(
        topBar = {
            GalleryAppBar(
                onBack = {},
                onConfirm = { onSelectImages(listOf()) },
                selectedCount = 0,
                maxSelections = maxSelection,
            )
        }
    ) { innerPadding ->
        when (imagesState) {
            is ResponseStatus.Loading -> { GalleryLoadingUI() }

            is ResponseStatus.Error -> { GalleryErrorUI(message = imagesState.message) }

            is ResponseStatus.Success -> {
                val images = imagesState.data

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = innerPadding,
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    itemsIndexed(
                        images,
                        key = { _, image -> image.id },
                        contentType = { _, _ -> "gallery_image" }
                    ) { _, image ->
                        val isSelected = selected.contains(image.id)

                        GalleryItem(
                            image = image,
                            isSelected = isSelected,
                            onToggle = {
                                selected = if (isSelected) selected - image.id
                                else if (selected.size < maxSelection) selected + image.id
                                else selected
                            }
                        )
                    }
                }
            }
        }

    }
}