package com.example.kmptraining.kmp_session5.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.kmp_session5.data.PlatformImage
import com.example.kmptraining.kmp_session5.data.PlatformImageThumbnail
import com.example.kmptraining.kmp_session5.utils.rememberGalleryImageSource
import com.example.kmptraining.kmp_session5.utils.rememberMediaPermissionManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    maxSelection: Int = 1000,
    onConfirm: (List<PlatformImage>) -> Unit,
) {
    val permissionManager = rememberMediaPermissionManager()
    val imageSource = rememberGalleryImageSource()

    var hasPermission by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }
    var images by remember { mutableStateOf<List<PlatformImage>>(emptyList()) }
    var selected by remember { mutableStateOf<Set<String>>(emptySet()) }

    LaunchedEffect(Unit) {
        hasPermission = permissionManager.requestGalleryPermission()
        if (hasPermission) {
            images = imageSource.loadImages(limit = maxSelection)
        }
        loading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "All Photos",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                },
                actions = {
                    Button(
                        onClick = {
                            val picked = images.filter { selected.contains(it.id) }
                            onConfirm(picked)
                        },
                        enabled = selected.isNotEmpty(),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Confirm (${selected.size}/$maxSelection)")
                    }
                }
            )
        }
    ) { padding ->
        when {
            loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            !hasPermission -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Gallery permission is required")
                }
            }

            images.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No images found")
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = padding,
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

@Composable
fun GalleryItem(
    image: PlatformImage,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(onClick = onToggle)
    ) {
        PlatformImageThumbnail(
            image = image,
            modifier = Modifier.fillMaxSize()
        )

        if (isSelected) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.35f))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryScreenPreview() {
    GalleryScreen(onConfirm = {})
}