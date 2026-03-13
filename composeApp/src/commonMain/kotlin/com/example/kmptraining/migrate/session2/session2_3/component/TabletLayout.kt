package com.example.kmptraining.migrate.session2.session2_3.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session2.session2_3.DashboardItem
import com.example.kmptraining.migrate.session2.session2_3.StatItem

// TODO: [Session 2] Bài tập 3 - Implement TabletLayout
// LazyVerticalGrid(GridCells.Fixed(2)): header + items(DashboardCard) 2 per row
@Composable
fun TabletLayout(
    stats: List<StatItem>,
    items: List<DashboardItem>,
    isPremium: Boolean,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (!isPremium) {
            item(span = { GridItemSpan(2) }) {
                PremiumBanner()
            }
        }

        item(span = { GridItemSpan(2) }) {
            StatsRow(stats = stats)
        }

        items(items) { item ->
            DashboardCard(item = item)
        }
    }
}
