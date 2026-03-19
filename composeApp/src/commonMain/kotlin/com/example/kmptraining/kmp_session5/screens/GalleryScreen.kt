package com.example.kmptraining.kmp_session5.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.kmptraining.kmp_session5.data.PlatformImage
import com.example.kmptraining.kmp_session5.data.PlatformImageThumbnail
import com.example.kmptraining.kmp_session5.utils.rememberGalleryImageSource
import com.example.kmptraining.kmp_session5.utils.rememberMediaPermissionManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    maxSelection: Int = 30,
    onConfirm: (List<PlatformImage>) -> Unit,
    onBack: () -> Unit = {}
) {
    val permissionManager = rememberMediaPermissionManager()
    val imageSource = rememberGalleryImageSource()

    var hasPermission by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }
    var images by remember { mutableStateOf<List<PlatformImage>>(emptyList()) }
    var selected by remember { mutableStateOf<Set<Int>>(emptySet()) }

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
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
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
                            val picked = selected.mapNotNull { index -> images.getOrNull(index) }
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
                    itemsIndexed(images) { index, image ->
                        val isSelected = selected.contains(index)

                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clickable {
                                    selected =
                                        if (isSelected) selected - index
                                        else if (selected.size < maxSelection) selected + index
                                        else selected
                                }
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
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryScreenPreview() {
    GalleryScreen(onConfirm = {})
}