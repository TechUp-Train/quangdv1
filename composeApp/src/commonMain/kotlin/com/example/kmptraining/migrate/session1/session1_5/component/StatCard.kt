package com.example.kmptraining.migrate.session1.session1_5.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.migrate.session1.session1_5.data.allPositiveStats

/**
 * Card hiển thị 1 stat với Slot API cho trend
 *
 * TODO: [Nâng cao] Tại sao Slot API tốt hơn truyền thẳng TrendIndicator vào?
 * → Caller có thể truyền bất kỳ UI nào vào (TrendIndicator, Chart, Badge...)
 */
@Composable
fun StatCard(
    label: String,
    value: String,
    emoji: String,
    modifier: Modifier = Modifier,
    // Slot API cho trend indicator — caller quyết định UI
    trend: @Composable () -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxHeight() // fillMaxHeight() phối hợp với IntrinsicSize.Max
                    .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // Header: emoji + label
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = emoji, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            // Value — số to
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )

            // Trend slot
            trend()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatCardPreview() {
    StatCard(
        label = allPositiveStats.first().label,
        value = allPositiveStats.first().value,
        emoji = allPositiveStats.first().emoji,
//        modifier = modifier
//            .weight(1f)
//            .fillMaxHeight(), // ← fillMaxHeight() phối hợp với IntrinsicSize
        trend = {
            TrendIndicator(
                percentage = allPositiveStats.first().percentage,
                isPositive = allPositiveStats.first().isPositive,
            )
        },
    )
}
