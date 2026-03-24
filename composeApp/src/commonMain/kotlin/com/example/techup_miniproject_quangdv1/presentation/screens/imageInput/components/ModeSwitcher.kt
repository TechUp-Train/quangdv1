package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.techup_miniproject_quangdv1.core.theme.GradientPrimaryEnd
import com.example.techup_miniproject_quangdv1.core.theme.GradientPrimaryStart
import com.example.techup_miniproject_quangdv1.core.utils.ImageMode

@Composable
fun ModeSwitcher(selectedMode: ImageMode, onModeSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 1.dp,
                color = Color.Black.copy(alpha = 0.1f)
            )
    ) {
        Text(
            "1 image",
            style = MaterialTheme.typography.titleMedium,
            color = if (selectedMode.requiredImageCount == 1) Color.White else Color.Black,
            modifier = Modifier.weight(1f)
                .background(
                    brush = Brush.linearGradient(
                        colors = if (selectedMode.requiredImageCount == 1) listOf(
                            GradientPrimaryStart,
                            GradientPrimaryEnd,
                        ) else listOf(Color.Transparent, Color.Transparent)
                    )
                )
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .clickable { onModeSelected(1) },
            textAlign = TextAlign.Center,
        )
        Text(
            "2 image",
            style = MaterialTheme.typography.titleMedium,
            color = if (selectedMode.requiredImageCount == 2) Color.White else Color.Black,
            modifier = Modifier.weight(1f)
                .background(
                    brush = Brush.linearGradient(
                        colors = if (selectedMode.requiredImageCount == 2) listOf(
                            GradientPrimaryStart,
                            GradientPrimaryEnd,
                        ) else listOf(Color.Transparent, Color.Transparent)
                    )
                )
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .clickable { onModeSelected(2) },
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SwitcherPreview() {
    ModeSwitcher(
        selectedMode = ImageMode.IMAGE_EDITING,
        onModeSelected = {}
    )
}