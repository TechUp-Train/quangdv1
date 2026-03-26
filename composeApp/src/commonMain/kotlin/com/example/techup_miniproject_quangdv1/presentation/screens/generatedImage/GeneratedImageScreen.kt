package com.example.techup_miniproject_quangdv1.presentation.screens.generatedImage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.techup_miniproject_quangdv1.core.theme.surfaceLightVariant
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.InteracButton
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.StateDialog
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratedImageScreen(
    url: String,
    onBack: () -> Unit,
    viewModel: GeneratedImageViewModel = koinViewModel()
) {
    val downloadState = viewModel.downloadState.collectAsStateWithLifecycle().value

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(downloadState) {
        when (downloadState) {
            is ResponseStatus.Success -> {
                snackBarHostState.showSnackbar(
                    message = "Image saved successfully to: ${downloadState.data}",
                    duration = SnackbarDuration.Long
                )
                viewModel.resetDownloadState()
            }

            else -> {}
        }
    }

    Scaffold(
        containerColor = surfaceLightVariant,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.matchParentSize()
                    .padding(horizontal = 20.dp)
            ) {
                AsyncImage(
                    model = url,
                    contentDescription = "generated image",
                    modifier = Modifier.weight(1f)
                )
                InteracButton(
                    text = "Download photo",
                    onClick = { viewModel.downloadImage(url) },
                )
            }
            when (downloadState) {
                is ResponseStatus.Loading -> {
                    StateDialog(
                        title = "Downloading",
                        message = "Saving your image...",
                        showButton = false,
                        isLoading = true,
                        onButtonClick = {},
                        onDismissRequest = {}
                    )
                }

                is ResponseStatus.Error -> {
                    StateDialog(
                        title = "Download Failed",
                        message = downloadState.message,
                        buttonText = "Close",
                        showButton = true,
                        isLoading = false,
                        onButtonClick = { viewModel.resetDownloadState() },
                        onDismissRequest = { viewModel.resetDownloadState() }
                    )
                }

                else -> {}
            }
        }
    }
}