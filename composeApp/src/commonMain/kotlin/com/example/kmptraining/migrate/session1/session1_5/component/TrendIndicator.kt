package com.example.kmptraining.migrate.session1.session1_5.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session1.session1_5.data.allPositiveStats

/**
 * Trend indicator: icon mũi tên + percentage + màu
 *
 * TODO: [Nâng cao - Compose Phase] So sánh 2 cách rotate icon:
 *
 * // ❌ Cách 1: Modifier.rotate() — trigger cả 3 phases (Composition + Layout + Drawing)
 * Icon(modifier = Modifier.rotate(if (isPositive) 0f else 180f))
 *
 * // ✅ Cách 2: graphicsLayer — skip Composition + Layout, chỉ Drawing
 * Icon(modifier = Modifier.graphicsLayer { rotationZ = if (isPositive) 0f else 180f })
 *
 * Với static UI cả 2 cho kết quả giống nhau, nhưng trong animation thì graphicsLayer
 * hiệu quả hơn nhiều vì không trigger layout pass.
 */
@Composable
fun TrendIndicator(
    percentage: String,
    isPositive: Boolean,
    modifier: Modifier = Modifier,
) {
    val color =
        if (isPositive) {
            MaterialTheme.colorScheme.tertiary
        } else {
            MaterialTheme.colorScheme.error
        }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        // TODO: [Nâng cao] Thay Modifier.rotate bằng graphicsLayer để skip Layout phase
        // Đây là ví dụ thực tế của Slide 11 — Smart Optimization
        Icon(
            imageVector = if (isPositive) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = if (isPositive) "Tăng" else "Giảm",
            tint = color,
            modifier =
                Modifier
                    .size(16.dp)
                    // ✅ graphicsLayer: chỉ Drawing phase — không trigger Layout
                    .graphicsLayer {
                        // Không cần rotate icon vì dùng TrendingUp/TrendingDown riêng
                        // Nhưng nếu chỉ dùng 1 icon, rotate ở đây:
                        // rotationZ = if (isPositive) 0f else 180f
                    },
        )

        Text(
            text = percentage,
            style = MaterialTheme.typography.bodySmall,
            color = color,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TrendIndicatorPreview() {
    TrendIndicator(
        percentage = allPositiveStats.first().percentage,
        isPositive = allPositiveStats.first().isPositive,
    )
}
