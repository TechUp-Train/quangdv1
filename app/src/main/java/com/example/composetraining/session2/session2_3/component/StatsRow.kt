package com.example.composetraining.session2.session2_3.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.session2.session2_3.StatItem

// TODO: [Session 2] Bài tập 3 - Implement StatsRow (dùng IntrinsicSize.Min)
// Row(Modifier.height(IntrinsicSize.Min).fillMaxWidth()) {
//     stats.forEachIndexed { index, stat ->
//         StatColumn(stat, Modifier.weight(1f))
//         if (index < stats.size - 1) VerticalDivider()
//     }
// }
@Composable
fun StatsRow(
    stats: List<StatItem>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        stats.forEachIndexed { index, stat ->
            StatColumn(
                stat = stat,
                modifier = Modifier.weight(1f),
            )
            if (index < stats.size - 1) {
                VerticalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                )
            }
        }
    }
}

@Composable
private fun StatColumn(
    stat: StatItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stat.value + if (stat.unit.isNotEmpty()) " ${stat.unit}" else "",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = stat.label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatsRowPreview() {
    StatsRow(
        stats =
            listOf(
                StatItem("Apps", "19", "apps"),
                StatItem("Downloads", "2.4M", ""),
                StatItem("Rating", "4.8", "⭐"),
                StatItem("Reviews", "12K", ""),
            ),
    )
}
