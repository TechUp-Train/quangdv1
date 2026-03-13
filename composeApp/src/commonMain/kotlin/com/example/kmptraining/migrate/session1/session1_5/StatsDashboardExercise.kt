package com.example.kmptraining.migrate.session1.session1_5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session1.session1_5.component.StatCard
import com.example.kmptraining.migrate.session1.session1_5.component.TrendIndicator
import com.example.kmptraining.migrate.session1.session1_5.data.StatData
import com.example.kmptraining.migrate.session1.session1_5.data.allPositiveStats
import com.example.kmptraining.migrate.session1.session1_5.data.mixedStats
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐⭐⭐ BÀI TẬP NÂNG CAO: Stats Dashboard
 *
 * Yêu cầu:
 * 1. StatCard với Slot API cho trend indicator
 * 2. TrendIndicator component (icon + %, màu xanh/đỏ)
 * 3. Dashboard 2x2 grid dùng Row + Column
 * 4. EQUAL HEIGHT cards — bắt buộc dùng IntrinsicSize.Max + fillMaxHeight()
 * 5. Compose Phase optimization — TrendIcon dùng graphicsLayer thay vì rotate()
 * 6. 3 @Preview: All Positive / Mixed / Dark Mode
 *
 * Khái niệm áp dụng từ Buổi 1:
 * - Slot API (trend: @Composable () -> Unit)
 * - IntrinsicSize.Max — equal height trick (học từ slide 6 Modifier)
 * - graphicsLayer { rotationZ } — skip Layout phase (Slide 11!)
 * - Modifier chain & order
 */

/**
 * Dashboard chứa 4 StatCards trong 2x2 grid
 *
 * TODO: [Nâng cao] Key implementation — IntrinsicSize.Max cho equal height
 *
 * Vấn đề: 2 card trong Row có content khác nhau → height khác nhau → layout không đều
 *
 * Solution:
 * Row(modifier = Modifier.height(IntrinsicSize.Max)) {
 *     StatCard(modifier = Modifier.weight(1f).fillMaxHeight())
 *     StatCard(modifier = Modifier.weight(1f).fillMaxHeight())
 * }
 *
 * Cơ chế: IntrinsicSize.Max đo height của card cao nhất, rồi set cho tất cả
 */
@Composable
fun StatsDashboard(
    stats: List<StatData>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "📊 Dashboard",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Row 1 — 2 stat cards EQUAL HEIGHT
            // TODO: [Nâng cao] Thêm Modifier.height(IntrinsicSize.Max) vào Row
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max),
                // ← EQUAL HEIGHT magic
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (stats.isNotEmpty()) {
                    StatCard(
                        label = stats[0].label,
                        value = stats[0].value,
                        emoji = stats[0].emoji,
                        modifier =
                            Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                        // ← fillMaxHeight() phối hợp với IntrinsicSize
                        trend = {
                            TrendIndicator(
                                percentage = stats[0].percentage,
                                isPositive = stats[0].isPositive,
                            )
                        },
                    )
                }
                if (stats.size > 1) {
                    StatCard(
                        label = stats[1].label,
                        value = stats[1].value,
                        emoji = stats[1].emoji,
                        modifier =
                            Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                        trend = {
                            TrendIndicator(
                                percentage = stats[1].percentage,
                                isPositive = stats[1].isPositive,
                            )
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Row 2 — 2 stat cards EQUAL HEIGHT
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (stats.size > 2) {
                    StatCard(
                        label = stats[2].label,
                        value = stats[2].value,
                        emoji = stats[2].emoji,
                        modifier =
                            Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                        trend = {
                            TrendIndicator(
                                percentage = stats[2].percentage,
                                isPositive = stats[2].isPositive,
                            )
                        },
                    )
                }
                if (stats.size > 3) {
                    StatCard(
                        label = stats[3].label,
                        value = stats[3].value,
                        emoji = stats[3].emoji,
                        modifier =
                            Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                        trend = {
                            TrendIndicator(
                                percentage = stats[3].percentage,
                                isPositive = stats[3].isPositive,
                            )
                        },
                    )
                }
            }
        }
    }
}

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Dashboard — All Positive")
@Composable
private fun DashboardAllPositivePreview() {
    ComposeTrainingTheme {
        StatsDashboard(stats = allPositiveStats)
    }
}

@Preview(showBackground = true, name = "Dashboard — Mixed Trends")
@Composable
private fun DashboardMixedPreview() {
    ComposeTrainingTheme {
        StatsDashboard(stats = mixedStats)
    }
}

@Preview(
    showBackground = true,
    name = "Dashboard — Dark Mode",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun DashboardDarkPreview() {
    ComposeTrainingTheme {
        StatsDashboard(stats = mixedStats)
    }
}

// ─── Câu Hỏi Thảo Luận ───────────────────────────────────────────────────────
/*
 * Sau khi hoàn thành, thảo luận với nhóm:
 *
 * Q1: Tại sao IntrinsicSize.Max lại work? Compose tính height như thế nào?
 *     → Compose đo "intrinsic height" (chiều cao tự nhiên) của mỗi child,
 *       lấy max, rồi constrain tất cả về cùng height đó.
 *
 * Q2: Nếu không dùng IntrinsicSize.Max thì UI trông thế nào?
 *     → Mỗi card height = content của nó → 2 card không cùng height → xấu.
 *
 * Q3: graphicsLayer vs Modifier.rotate() — khi nào dùng cái nào?
 *     → Animation → graphicsLayer (skip Layout phase, GPU layer riêng)
 *     → Static rotation → cả 2 đều ok, graphicsLayer không có overhead lớn hơn
 *
 * Q4: Slot API ở StatCard có lợi gì so với truyền thẳng TrendIndicator?
 *     → Caller có thể thay bằng Chart, Badge, hay bất kỳ UI nào
 *     → StatCard không coupled với TrendIndicator
 */
