package com.example.composetraining.session4.session4_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐⭐ BÀI TẬP 3: Design System (Challenge)
 *
 * Yêu cầu:
 * - data class AppColors (success, warning, error, aiPrimary, trending, gradient)
 * - CompositionLocalProvider
 * - AppDesignTheme wrapper
 * - Product Card với:
 *   - "AI RECOMMENDED" badge (aiPrimary color)
 *   - "TRENDING" pill (trending color)
 *   - Price text (custom typography)
 * - AppDesignTheme.colors.xxx syntax hoạt động
 */

data class AppColors(
    val success: Color = Color(0xFF4CAF50),
    val warning: Color = Color(0xFFFFC107),
    val error: Color = Color(0xFFF44336),
    val aiPrimary: Color = Color(0xFF7C4DFF),
    val trending: Color = Color(0xFFFF6D00),
    val gradientStart: Color = Color(0xFF1A237E),
    val gradientEnd: Color = Color(0xFF7C4DFF),
)

val LocalAppColors = staticCompositionLocalOf { AppColors() }

object AppDesignTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current
}

@Composable
fun AppDesignThemeWrapper(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppColors provides AppColors()) {
        ComposeTrainingTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                content()
            }
        }
    }
}

@Composable
fun DesignSystemProductCard() {
    // Tạo Product Card với:
    // 1. "AI RECOMMENDED" badge — dùng AppDesignTheme.colors.aiPrimary
    // 2. Product image placeholder
    // 3. Product name + description
    // 4. "TRENDING" pill — dùng AppDesignTheme.colors.trending
    // 5. Price text — custom style
    AppDesignThemeWrapper {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .width(280.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.TopStart
                ) {
                    Surface(
                        color = AppDesignTheme.colors.aiPrimary,
                        shape = RoundedCornerShape(bottomEnd = 8.dp)
                    ) {
                        Text(
                            text = "AI RECOMMENDED",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    Surface(
                        color = AppDesignTheme.colors.trending.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(50),
                        border = null
                    ) {
                        Text(
                            text = "TRENDING",
                            color = AppDesignTheme.colors.trending,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Premium Wireless Headphones",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Experience studio-quality sound with adaptive noise cancellation technology.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "$299.00",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = AppDesignTheme.colors.gradientStart,
                            fontWeight = FontWeight.Black,
                            letterSpacing = (-1).sp
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DesignSystemProductCardPreview() {
    DesignSystemProductCard()
}
