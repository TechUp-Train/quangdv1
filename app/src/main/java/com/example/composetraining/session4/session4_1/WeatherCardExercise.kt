package com.example.composetraining.session4.session4_1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐ BÀI TẬP 1: Weather Card (Easy)
 *
 * Yêu cầu:
 * - Dùng MaterialTheme.colorScheme.primary/surface/onSurface
 * - Typography: headlineMedium cho temperature, bodyLarge cho description
 * - Shape: RoundedCornerShape(16.dp)
 * - Toggle dark mode bằng Switch
 * - Data: hardcode "Hanoi, 32°C, Sunny"
 */
@Composable
fun WeatherCardScreen() {
     var isDark by remember { mutableStateOf(false) }

    ComposeTrainingTheme(
        darkTheme = isDark,
        dynamicColor = false
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Dark Mode",
                    style = MaterialTheme.typography.titleMedium
                )
                Switch(
                    checked = isDark,
                    onCheckedChange = { isDark = it }
                )
            }

            // - Text "Hà Nội" (titleLarge, colorScheme.primary)
            // - Text "32°C" (headlineMedium, colorScheme.onSurface)
            // - Text "Sunny ☀️" (bodyLarge, colorScheme.onSurfaceVariant)
            // KHÔNG hardcode color — dùng MaterialTheme.colorScheme.xxx
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Hanoi",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = "32°C",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                    Text(
                        text = "Sunny ☀️",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun WeatherCardScreenPreview() {
    WeatherCardScreen()
}