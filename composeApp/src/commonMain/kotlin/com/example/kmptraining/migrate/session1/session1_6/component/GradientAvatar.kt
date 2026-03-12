package com.example.kmptraining.migrate.session1.session1_6.component

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.migrate.session1.session1_6.data.sampleProfile

/**
 * Avatar với GRADIENT BORDER — advanced Modifier trick
 *
 * TODO: [Nâng cao] Implement gradient border thay vì solid border
 *
 * Cách implement gradient border:
 * Box(modifier = Modifier
 *     .size(size.dp + 4.dp)  // Slightly larger for border
 *     .clip(CircleShape)
 *     .background(
 *         Brush.linearGradient(
 *             colors = listOf(Color(0xFF833AB4), Color(0xFFFD1D1D), Color(0xFFFCB045))
 *         )
 *     )
 * ) {
 *     Box(modifier = Modifier
 *         .size(size.dp)
 *         .clip(CircleShape)
 *         .background(MaterialTheme.colorScheme.surface)
 *         .align(Alignment.Center)
 *     ) {
 *         // Avatar content
 *     }
 * }
 */
@Composable
fun GradientAvatar(
    username: String,
    size: Int,
    modifier: Modifier = Modifier,
) {
    // TODO: [Nâng cao] Thay thế solid border bằng gradient border
    // Gradient border = Instagram-style purple→red→orange
    Box(
        modifier =
            modifier
                .size((size + 4).dp)
                .clip(CircleShape)
                .background(
                    // TODO: Đổi thành Brush.linearGradient với multiple colors
                    brush =
                        Brush.linearGradient(
                            colors =
                                listOf(
                                    Color(0xFF833AB4), // Instagram purple
                                    Color(0xFFFD1D1D), // Red
                                    Color(0xFFFCB045), // Orange/yellow
                                ),
                        ),
                ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .size(size.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = username.first().uppercaseChar().toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = (size / 2.5).sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GradientAvatarPreview() {
    GradientAvatar(username = sampleProfile.username, size = 72)
}
