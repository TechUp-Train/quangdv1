package com.example.kmptraining.migrate.session4.session4_5.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme
import androidx.compose.material3.FilterChip as MaterialFilterChip

@Composable
fun FilterChip(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MaterialFilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun FilterChipPreview() {
    ComposeTrainingTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                title = "All",
                selected = true,
                onClick = {}
            )
            FilterChip(
                title = "Nature",
                selected = false,
                onClick = {}
            )
        }
    }
}