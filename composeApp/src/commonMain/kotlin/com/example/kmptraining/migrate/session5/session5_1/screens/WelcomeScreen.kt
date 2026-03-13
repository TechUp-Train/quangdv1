package com.example.composetraining.session5.session5_1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

// Params: onGetStarted: () -> Unit
// UI: Column centered, Text "👋 Chào mừng đến với Compose!", Button "Bắt đầu"
@Composable
fun WelcomeScreen(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "👋 Chào mừng đến với Compose!",
            style = TextStyle(
                fontFamily = MaterialTheme.typography.headlineLarge.fontFamily
            )
        )

        Button(onClick = onGetStarted) {
            Text(
                "Bắt đầu"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    ComposeTrainingTheme {
        WelcomeScreen(onGetStarted = {})
    }
}