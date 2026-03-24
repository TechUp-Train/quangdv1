package com.example.techup_miniproject_quangdv1.presentation.screens.generatedImage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.techup_miniproject_quangdv1.core.theme.surfaceLightVariant
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.DialogContent
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.InteracButton
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratedImageScreen(
    url: String,
    onBack: () -> Unit,
    viewModel: GeneratedImageViewModel = koinViewModel()
) {
    val downloadState = viewModel.downloadState.collectAsStateWithLifecycle().value

    Scaffold(
        containerColor = surfaceLightVariant,
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
                is ResponseStatus.Idle -> {}
                is ResponseStatus.Loading -> {
                    BasicAlertDialog(
                        onDismissRequest = {},
                        content = { DialogContent("Downloading...") }
                    )
                }

                is ResponseStatus.Error -> {
                    BasicAlertDialog(
                        onDismissRequest = {},
                        content = { DialogContent("Error downloading: ${downloadState.message}") }
                    )
                }

                is ResponseStatus.Success -> {

                }
            }
        }
    }
}