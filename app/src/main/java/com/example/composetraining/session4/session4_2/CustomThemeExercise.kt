package com.example.composetraining.session4.session4_2

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐ BÀI TẬP 2: Custom Theme (Medium)
 *
 * Yêu cầu:
 * - Định nghĩa lightColorScheme + darkColorScheme riêng
 * - Custom Typography (có thể dùng system font)
 * - Tạo wrapper MyAppTheme { content }
 * - Áp dụng lên 2 screens: Home + Detail
 * - Consistent color usage (không hardcode hex trong composable)
 * - isSystemInDarkTheme() tự switch
 */

private val MyLightColors = lightColorScheme(
    primary = Color(0xFF145E75),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFEDF4F6),
    onPrimaryContainer = Color(0xFF10313F),
    secondary = Color(0xFF625B71),
    onSecondary = Color.White,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

private val MyDarkColors = darkColorScheme(
    primary = Color(0xFF88D2E8),
    onPrimary = Color(0xFF003643),
    primaryContainer = Color(0xFF004E60),
    onPrimaryContainer = Color(0xFFBCE9F6),
    secondary = Color(0xFFCCC2DC),
    onSecondary = Color(0xFF332D41),
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    onSurface = Color(0xFFE6E1E5),
)

private val MyTypography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
)

// TODO: [Session 4] Bài tập 2 - Tạo MyAppTheme wrapper composable
@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) MyDarkColors else MyLightColors,
        typography = MyTypography,
        content = content
    )
}

@Composable
fun CustomThemeHomeScreen() {
    // TODO: [Session 4] Bài tập 2 - Wrap trong MyAppTheme, tạo Home screen đơn giản
    // Card + Text dùng MaterialTheme.colorScheme + typography
    Text("Bắt đầu code Custom Theme ở đây!", modifier = Modifier.padding(16.dp))
}

@Composable
fun CustomThemeDetailScreen() {
    // TODO: [Session 4] Bài tập 2 - Detail screen cũng dùng MyAppTheme
    Text("Detail screen với Custom Theme", modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true)
@Composable
private fun CustomThemeHomeScreenPreview() {
    ComposeTrainingTheme { CustomThemeHomeScreen() }
}
