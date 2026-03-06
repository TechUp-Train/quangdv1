package com.example.composetraining.session2.session2_3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.session2.session2_3.component.PhoneLayout
import com.example.composetraining.session2.session2_3.component.TabletLayout
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐⭐ BÀI TẬP 3: Responsive Dashboard (Khó — 60 phút)
 *
 * Yêu cầu:
 * - BoxWithConstraints để detect screen width
 *   → Phone (maxWidth < 600.dp): LazyColumn, 1 card per row
 *   → Tablet (maxWidth ≥ 600.dp): LazyVerticalGrid, 2 columns
 * - Stats row: 4 stats cột ngang với VerticalDivider, dùng IntrinsicSize.Min để bằng cao
 * - Premium banner với Modifier.drawBehind (custom gradient background)
 *
 * Tiêu chí:
 * - BoxWithConstraints đúng cách (dùng maxWidth từ constraints)
 * - Modifier.height(IntrinsicSize.Min) + VerticalDivider trên stats row
 * - Modifier.drawBehind { drawRect(brush = gradient) } cho banner
 * - KHÔNG hardcode layout cho device type
 *
 * Gợi ý BoxWithConstraints:
 * BoxWithConstraints {
 *     if (maxWidth < 600.dp) {
 *         PhoneLayout(stats, items, isPremium)
 *     } else {
 *         TabletLayout(stats, items, isPremium)
 *     }
 * }
 *
 * Gợi ý drawBehind:
 * Modifier.drawBehind {
 *     drawRect(
 *         brush = Brush.horizontalGradient(listOf(Color(0xFF6750A4), Color(0xFF9C27B0)))
 *     )
 * }
 */

data class StatItem(
    val label: String,
    val value: String,
    val unit: String = ""
)

data class DashboardItem(
    val id: Int,
    val title: String,
    val description: String,
    val category: String
)

private val sampleStats = listOf(
    StatItem("Apps", "19", "apps"),
    StatItem("Downloads", "2.4M", ""),
    StatItem("Rating", "4.8", "⭐"),
    StatItem("Reviews", "12K", "")
)

private val sampleItems = (1..8).map { i ->
    DashboardItem(
        i,
        "App #$i",
        "Mô tả app $i ngắn gọn",
        listOf("AI", "Photo", "Video", "Utility")[i % 4]
    )
}

// TODO: [Session 2] Bài tập 3 - Implement DashboardScreen
// Dùng BoxWithConstraints để switch layout:
@Composable
fun DashboardScreen(
    stats: List<StatItem> = sampleStats,
    items: List<DashboardItem> = sampleItems,
    isPremium: Boolean = false
) {
    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            PhoneLayout(stats, items, isPremium)
        } else {
            TabletLayout(stats, items, isPremium)
        }
    }
}

@Composable
fun ResponsiveDashboardScreen() {
    DashboardScreen()
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun PhonePreview() {
    ComposeTrainingTheme { ResponsiveDashboardScreen() }
}

@Preview(showBackground = true, widthDp = 720)
@Composable
private fun TabletPreview() {
    ComposeTrainingTheme { ResponsiveDashboardScreen() }
}