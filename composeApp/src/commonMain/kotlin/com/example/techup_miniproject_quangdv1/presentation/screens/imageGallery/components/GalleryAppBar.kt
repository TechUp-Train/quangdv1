package com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.techup_miniproject_quangdv1.core.theme.AppTheme
import com.example.techup_miniproject_quangdv1.core.theme.GradientPrimaryEnd
import com.example.techup_miniproject_quangdv1.core.theme.GradientPrimaryStart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryAppBar(
    onBack: () -> Unit,
    onConfirm: () -> Unit,
    selectedCount: Int = 0,
    maxSelections: Int = 2,
) {
    TopAppBar(
        title = {
            Text(
                "All Photos",
                style = MaterialTheme.typography.titleMedium,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            TextButton(
                onClick = onConfirm,
                enabled = maxSelections > 0,
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(
                            brush =
                                Brush.linearGradient(
                                    colors =
                                        listOf(
                                            GradientPrimaryStart,
                                            GradientPrimaryEnd,
                                        ),
                                ),
                        ).padding(horizontal = 10.dp, vertical = 2.dp),
            ) {
                Text(
                    "Confirm ($selectedCount/$maxSelections)",
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                )
            }
        },
    )
}

@Preview
@Composable
private fun GalleryAppBarPreview() {
    AppTheme {
        GalleryAppBar(
            onBack = {},
            onConfirm = {},
            selectedCount = 1,
            maxSelections = 2,
        )
    }
}
