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
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.techup_miniproject_quangdv1.core.theme.surfaceLightVariant
import com.example.techup_miniproject_quangdv1.core.utils.ConnectivityStatus
import com.example.techup_miniproject_quangdv1.core.utils.ImageMode
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.DualImageSingleBorderFrame
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.ModeSwitcher
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.ModeView
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.PromptInputView
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.StylesListView
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.DialogContent
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.InteracButton
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.RoundedImageFrame
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageInputScreen(
    onSelectImages: (ImageMode) -> Unit,
    onGeneratedResult: (String) -> Unit,
    viewModel: ImageInputViewModel = koinViewModel()
) {
    val promptText = viewModel.promptText.collectAsStateWithLifecycle().value
    val imageMode = viewModel.imageMode.collectAsStateWithLifecycle().value

    val selectedImages = viewModel.selectedImages.collectAsStateWithLifecycle().value
    val stylesState = viewModel.stylesState.collectAsStateWithLifecycle().value
    val selectedCategoryIndex = viewModel.selectedCategoryIndex.collectAsStateWithLifecycle().value
    val selectedStyleId = viewModel.selectedStyleId.collectAsStateWithLifecycle().value

    val generatedResult = viewModel.generateProcessStatus.collectAsStateWithLifecycle().value

    val connectivity = viewModel.connectivity.collectAsStateWithLifecycle().value
    var lastConnectivity by remember { mutableStateOf(ConnectivityStatus.Unavailable) }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(generatedResult) {
        if (generatedResult is ResponseStatus.Success) {
            onGeneratedResult(generatedResult.data)
        }
    }

    LaunchedEffect(connectivity) {
        if (lastConnectivity != ConnectivityStatus.Unavailable &&
            connectivity != lastConnectivity
        ) {
            snackBarHostState.currentSnackbarData?.dismiss()

            when (connectivity) {
                ConnectivityStatus.Offline -> {
                    snackBarHostState.showSnackbar("You're offline")
                }
                ConnectivityStatus.Online -> {
                    snackBarHostState.showSnackbar(
                        "Back online",
                        duration = SnackbarDuration.Short
                    )
                }
                else -> {}
            }
        }

        lastConnectivity = connectivity
    }

    Scaffold(
        containerColor = surfaceLightVariant,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { contentPadding ->
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
                .padding(contentPadding)
        ) {
            val screenHeight = maxHeight
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                ModeSwitcher(
                    selectedMode = imageMode,
                    onModeSelected = { mode ->
                        viewModel.setImageMode(ImageMode.entries.first { it.requiredImageCount == mode })
                    }
                )

                Spacer(modifier = Modifier.height(2.dp))

                PromptInputView(
                    modifier = Modifier.height(screenHeight * 0.1f),
                    prompt = promptText,
                    onPromptChange = { newPrompt ->
                        viewModel.setPromptText(newPrompt)
                    },
                )

                if (imageMode.requiredImageCount == 1) {
                    RoundedImageFrame(
                        image = selectedImages.firstOrNull(),
                        modifier = Modifier.height(screenHeight * 0.5f),
                        onChangeImage = { onSelectImages(imageMode) }
                    )
                } else {
                    DualImageSingleBorderFrame(
                        image1 = selectedImages.getOrNull(0),
                        image2 = selectedImages.getOrNull(1),
                        onChangeImage1 = { onSelectImages(imageMode) },
                        onChangeImage2 = { onSelectImages(imageMode) },
                        modifier = Modifier.height(screenHeight * 0.5f)
                    )
                }

                ModeView(
                    selectedMode = imageMode,
                    requiredImageCount = imageMode.requiredImageCount,
                    onModeSelected = { mode ->
                        viewModel.setImageMode(mode)
                    }
                )


                if (stylesState is ResponseStatus.Success) {
                    StylesListView(
                        categories = stylesState.data,
                        selectedCategoryIndex = selectedCategoryIndex,
                        selectedStyleId = selectedStyleId,
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
                    enabled = selectedImages.size >= imageMode.requiredImageCount && connectivity == ConnectivityStatus.Online
                )
            }

            when(generatedResult) {
                is ResponseStatus.Loading -> {
                    BasicAlertDialog(
                        onDismissRequest = {},
                        content = {
                            DialogContent(generatedResult.message ?: "Generating...")
                        },
                        properties = DialogProperties(
                            dismissOnClickOutside = true,
                            dismissOnBackPress = true,
                        )
                    )
                }
                is ResponseStatus.Error -> {
                    BasicAlertDialog(
                        onDismissRequest = { viewModel.resetGenerateStatus() },
                        content = {
                            DialogContent("Error generating: ${generatedResult.message}")
                        }
                    )
                }
                is ResponseStatus.Success -> {}
                is ResponseStatus.Idle -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageInputScreenPreview() {
    ImageInputScreen({}, {})
}
