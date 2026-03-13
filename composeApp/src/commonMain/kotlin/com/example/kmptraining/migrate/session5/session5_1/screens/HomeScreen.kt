package com.example.kmptraining.migrate.session5.session5_1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

// Params: onLogout: () -> Unit
// UI: Column centered, Text "🏠 Trang chủ", OutlinedButton "Đăng xuất"
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "🏠 Trang chủ",
            style = MaterialTheme.typography.headlineLarge
        )
        OutlinedButton(onClick = onLogout) {
            Text("Đăng xuất")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ComposeTrainingTheme {
        HomeScreen(onLogout = {})
    }
}
