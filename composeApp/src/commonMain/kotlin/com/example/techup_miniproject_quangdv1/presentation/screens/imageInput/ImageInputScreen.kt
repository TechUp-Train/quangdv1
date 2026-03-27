package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.techup_miniproject_quangdv1.core.theme.surfaceLightVariant
import com.example.techup_miniproject_quangdv1.core.utils.ImageMode
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.DualImageSingleBorderFrame
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.ModeSwitcher
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.ModeView
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.PromptInputView
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.StylesListView
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.InteracButton
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.RoundedImageFrame
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.StateDialog
import com.plusmobileapps.konnectivity.Konnectivity
import com.plusmobileapps.konnectivity.NetworkConnection
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageInputScreen(
    onSelectImages: (ImageMode) -> Unit,
    onGeneratedResult: (String) -> Unit,
    viewModel: ImageInputViewModel = koinViewModel(),
) {
    val promptText = viewModel.promptText.collectAsStateWithLifecycle().value
    val imageMode = viewModel.imageMode.collectAsStateWithLifecycle().value

    val selectedImages = viewModel.selectedImages.collectAsStateWithLifecycle().value
    val stylesState = viewModel.stylesState.collectAsStateWithLifecycle().value
    val selectedCategory = viewModel.selectedCategory.collectAsStateWithLifecycle().value
    val selectedStyle = viewModel.selectedStyleId.collectAsStateWithLifecycle().value

    val generatedResult = viewModel.generateProcessStatus.collectAsStateWithLifecycle().value

    val konnectivity = remember { Konnectivity() }
    val networkConnection by konnectivity.currentNetworkConnectionState.collectAsStateWithLifecycle()
    val isOnline = networkConnection != NetworkConnection.NONE

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(generatedResult) {
        if (generatedResult is ResponseStatus.Success) {
            onGeneratedResult(generatedResult.data)
            viewModel.resetGenerateStatus()
        }
    }

    LaunchedEffect(networkConnection) {
        if (networkConnection == NetworkConnection.NONE) {
            snackBarHostState.showSnackbar("You're offline")
        }
    }

    Scaffold(
        containerColor = surfaceLightVariant,
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { contentPadding ->
        BoxWithConstraints(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
        ) {
            val screenHeight = maxHeight
            val scrollState = rememberScrollState()

            Column(
                modifier =
                    Modifier
                        .padding(horizontal = 10.dp)
                        .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                ModeSwitcher(
                    selectedMode = imageMode ?: ImageMode.IMAGE_EDITING,
                    onModeSelected = { mode ->
                        viewModel.setImageMode(ImageMode.entries.first { it.requiredImageCount == mode })
                    },
                )

                Spacer(modifier = Modifier.height(2.dp))

                PromptInputView(
                    modifier = Modifier.height(screenHeight * 0.1f),
                    prompt = promptText,
                    onPromptChange = { newPrompt ->
                        viewModel.setPromptText(newPrompt)
                    },
                )

                if (imageMode?.requiredImageCount == 2) {
                    DualImageSingleBorderFrame(
                        image1 = selectedImages.firstOrNull(),
                        image2 = selectedImages.lastOrNull(),
                        onChangeImage1 = { onSelectImages(imageMode) },
                        onChangeImage2 = { onSelectImages(imageMode) },
                        modifier = Modifier.height(screenHeight * 0.5f),
                    )
                } else {
                    RoundedImageFrame(
                        image = selectedImages.firstOrNull(),
                        modifier = Modifier.height(screenHeight * 0.5f),
                        onChangeImage = { onSelectImages(imageMode ?: ImageMode.IMAGE_EDITING) },
                    )
                }

                ModeView(
                    selectedMode = imageMode,
                    requiredImageCount = imageMode?.requiredImageCount ?: 1,
                    onModeSelected = { mode ->
                        viewModel.setImageMode(mode)
                    },
                )

                if (stylesState is ResponseStatus.Success) {
                    StylesListView(
                        categories = stylesState.data,
                        selectedCategory = selectedCategory ?: stylesState.data.first(),
                        selectedStyle = selectedStyle,
                        onCategorySelected = { index -> viewModel.selectCategory(index) },
                        onStyleSelected = { styleId -> viewModel.selectStyle(styleId) },
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                InteracButton(
                    "Generate AI",
                    onClick = {
                        viewModel.processImage()
                    },
                    enabled =
                        selectedImages.size >= (
                            imageMode?.requiredImageCount
                                ?: 1
                        ) && isOnline,
                )
            }

            when (generatedResult) {
                is ResponseStatus.Loading -> {
                    StateDialog(
                        title = "Generating",
                        message = generatedResult.message ?: "Processing your images...",
                        showButton = false,
                        isLoading = true,
                        onButtonClick = {},
                        onDismissRequest = {},
                    )
                }

                is ResponseStatus.Error -> {
                    StateDialog(
                        title = "Error",
                        message = generatedResult.message,
                        buttonText = "Close",
                        showButton = true,
                        isLoading = false,
                        onButtonClick = { viewModel.resetGenerateStatus() },
                        onDismissRequest = { viewModel.resetGenerateStatus() },
                    )
                }

                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageInputScreenPreview() {
    ImageInputScreen({}, {})
}
