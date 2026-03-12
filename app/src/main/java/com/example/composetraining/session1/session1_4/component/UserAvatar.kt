package com.example.composetraining.session1.session1_4.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserAvatar(
    username: String,
    modifier: Modifier = Modifier,
) {
    // TODO: [Nâng cao] Tính màu từ username.hashCode() thay vì hardcode
    // Gợi ý:
    // val avatarColors = listOf(Color(0xFF1DA1F2), Color(0xFFE0245E), ...)
    // val color = avatarColors[abs(username.hashCode()) % avatarColors.size]

    val avatarColors =
        listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.tertiary,
        )
    val color = avatarColors[Math.abs(username.hashCode()) % avatarColors.size]

    Box(
        modifier =
            modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = username.first().uppercaseChar().toString(),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserAvatarPreview() {
    UserAvatar(username = "QuangDV")
}
