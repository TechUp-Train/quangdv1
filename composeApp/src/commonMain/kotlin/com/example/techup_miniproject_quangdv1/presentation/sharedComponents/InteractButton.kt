package com.example.techup_miniproject_quangdv1.presentation.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.techup_miniproject_quangdv1.core.theme.GradientAccentEnd
import com.example.techup_miniproject_quangdv1.core.theme.GradientAccentStart

@Composable
fun InteracButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    TextButton(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    brush =
                        Brush.linearGradient(
                            colors =
                                listOf(
                                    GradientAccentStart.copy(alpha = if (enabled) 1f else 0.3f),
                                    GradientAccentEnd.copy(alpha = if (enabled) 1f else 0.3f),
                                ),
                        ),
                ),
        onClick = { if (enabled) onClick() },
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InteracButtonPreview() {
    InteracButton(text = "Generate AI", onClick = {}, enabled = false)
}
