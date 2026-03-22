package com.example.techup_miniproject_quangdv1.presentation.screens.imageInputScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.techup_miniproject_quangdv1.core.config.AppBuildConfig

@Composable
fun ImageInputScreen() {
    Text(
        AppBuildConfig.PUBLIC_KEY,
        style = TextStyle(
            color = Color.Black
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ImageInputScreenPreview() {
    ImageInputScreen()
}