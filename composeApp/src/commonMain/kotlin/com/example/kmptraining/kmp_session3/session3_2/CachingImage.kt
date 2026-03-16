package com.example.kmptraining.kmp_session3.session3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import com.example.kmptraining.kmp_session3.utils.createImageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CachingImage() {
    val context = LocalPlatformContext.current
    val imageLoader = remember { createImageLoader(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Caching Image")
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            SubcomposeAsyncImage(
                model = "https://picsum.photos/400/300?random=${remember { (0..100).random() }}",
                contentDescription = "Random Network Image",
                imageLoader = imageLoader,
                loading = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(16.dp),
                            color = colorScheme.primary
                        )
                    }
                },
                error = { state ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Failed to load image",
                            style = MaterialTheme.typography.titleMedium,
                            color = colorScheme.error
                        )
                        state.result.throwable.let { throwable ->
                            Text(
                                text = throwable.message ?: "Unknown Error",
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                },
                filterQuality = FilterQuality.High,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CachingImagePreview() {
    CachingImage()
}