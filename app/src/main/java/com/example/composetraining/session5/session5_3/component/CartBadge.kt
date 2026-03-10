package com.example.composetraining.session5.session5_3.component

import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetraining.ui.theme.ComposeTrainingTheme

@Composable
fun ECommerceBadge(
    count: Int,
    modifier: Modifier = Modifier
) {
    if (count > 0) {
        Badge(
            modifier = modifier,
            containerColor = Color(0xFFC62828),
            contentColor = Color.White
        ) {
            Text(
                text = if (count > 99) "99+" else count.toString(),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview
@Composable
private fun ECommerceBadgePreview() {
    ComposeTrainingTheme {
        ECommerceBadge(count = 5)
    }
}