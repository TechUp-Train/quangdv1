package com.example.kmptraining.migrate.session3.session3_4.component.reviewStep

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

@Composable
fun ReviewRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    // - Row(fillMaxWidth, SpaceBetween): Text label (onSurfaceVariant) + Text value (Medium)
    Row(
        modifier =
            modifier
                .padding(vertical = 1.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewRowPreview() {
    ComposeTrainingTheme {
        ReviewRow(
            label = "Full Name",
            value = "John Doe",
        )
    }
}
