package com.example.composetraining.session2.session2_3.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composetraining.session2.session2_3.DashboardItem
import com.example.composetraining.session2.session2_3.StatItem

// TODO: [Session 2] Bài tập 3 - Implement PhoneLayout
// LazyColumn: PremiumBanner + StatsRow + items(DashboardCard) 1 per row
@Composable
fun PhoneLayout(
    stats: List<StatItem>,
    items: List<DashboardItem>,
    isPremium: Boolean,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (!isPremium) {
            item {
                PremiumBanner()
            }
        }
        
        item { 
            StatsRow(stats = stats) 
        }

        items(items) { item ->
            DashboardCard(item = item)
        }
    }
}
