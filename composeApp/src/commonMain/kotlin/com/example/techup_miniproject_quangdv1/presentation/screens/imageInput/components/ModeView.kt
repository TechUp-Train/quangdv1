package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techup_miniproject_quangdv1.core.theme.BrandMagenta
import com.example.techup_miniproject_quangdv1.core.utils.ImageMode
import com.example.techup_miniproject_quangdv1.core.utils.displayName

@Composable
fun ModeView(
    selectedMode: ImageMode?,
    requiredImageCount: Int,
    onModeSelected: (ImageMode) -> Unit,
) {
    val modes =
        ImageMode.entries.filter {
            it.requiredImageCount == requiredImageCount
        }

    Column {
        Text(
            "Choose Mode",
            style =
                MaterialTheme.typography.titleLarge.copy(
                    color = BrandMagenta,
                ),
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            horizontalArrangement = Arrangement.Start,
        ) {
            modes.forEach { mode ->
                ModeItem(
                    mode = mode,
                    isSelected = selectedMode == mode,
                    onClick = { onModeSelected(mode) },
                )
            }
        }
    }
}

@Composable
fun ModeItem(
    mode: ImageMode,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .padding(6.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (isSelected) {
                        BrandMagenta
                    } else {
                        Color.Transparent
                    },
                ).border(
                    width = 1.dp,
                    color = BrandMagenta,
                    shape = RoundedCornerShape(20.dp),
                ).clickable { onClick() }
                .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Text(
            text = mode.displayName(),
            color = if (isSelected) Color.White else BrandMagenta,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
