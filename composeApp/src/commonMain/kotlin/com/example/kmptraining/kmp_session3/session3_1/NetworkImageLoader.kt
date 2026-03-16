package com.example.kmptraining.kmp_session3.session3_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkImageLoader() {
    var imageUrl by rememberSaveable { mutableStateOf("https://picsum.photos/400/300?random=1") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Network Image")
                },
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = "Network Image",
                loading = {
                    Box(contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                },
                error = {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "Failed to load image")
                    }
                },
                filterQuality = FilterQuality.High,
            )
            Spacer(Modifier.height(10.dp))
            Button(onClick = { imageUrl = "" }) {
                Text(text = "Test failed state")
            }
        }
    }
}

@Preview
@Composable
fun NetworkImageLoaderPreview() {
    NetworkImageLoader()
}
