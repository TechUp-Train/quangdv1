package com.example.kmptraining

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmptraining.kmp_session5.screens.GalleryScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        GalleryScreen(
            onConfirm = {
                println("Selected images: $it")
            }
        )
    }
}