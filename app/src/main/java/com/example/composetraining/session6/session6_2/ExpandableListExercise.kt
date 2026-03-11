package com.example.composetraining.session6.session6_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apero.composetraining.common.SampleData
import com.example.composetraining.session6.session6_2.component.FaqItemCard
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐ BÀI TẬP 2: Expandable FAQ List (Medium)
 *
 * Yêu cầu:
 * - LazyColumn: 5 câu hỏi FAQ
 * - Click question → AnimatedVisibility (answer expand/collapse)
 * - Arrow icon rotate 0° → 180° (animateFloatAsState)
 * - animateContentSize trên answer text
 * - Chỉ 1 item expand tại 1 thời điểm
 */

@Composable
fun ExpandableListScreen() {
    val faqItems = SampleData.faqItems

    var expandedItemId by remember { mutableIntStateOf(-1) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("FAQ", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(faqItems, key = { it.id }) { faq ->
                FaqItemCard(
                    faq = faq,
                    isExpanded = expandedItemId == faq.id,
                    onToggle = { expandedItemId = if (expandedItemId == faq.id) -1 else faq.id }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ExpandableListScreenPreview() {
    ComposeTrainingTheme { ExpandableListScreen() }
}
