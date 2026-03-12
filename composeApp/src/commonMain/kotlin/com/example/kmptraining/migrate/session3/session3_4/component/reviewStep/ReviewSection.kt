package com.example.kmptraining.migrate.session3.session3_4.component.reviewStep

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

@Composable
fun ReviewSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    // - Card(fillMaxWidth, surfaceVariant color) { Column(padding=12.dp) { Text title + content() } }
    Card(
        modifier =
            modifier
                .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.LightGray),
    ) {
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(12.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier =
                        Modifier.height(2.dp)
                            .width(1.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.Blue.copy(alpha = 0.6f)),
                )
                Text(
                    text = title,
                    style =
                        MaterialTheme.typography.titleMedium.copy(
                            color = Color.Blue.copy(alpha = 0.6f),
                        ),
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 2.dp),
                thickness = 1.dp,
                color = Color.LightGray,
            )
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewSectionPreview() {
    ComposeTrainingTheme {
        ReviewSection(
            title = "Personal Information",
            modifier = Modifier.padding(16.dp),
        ) {
            ReviewRow(
                label = "Full Name",
                value = "John Doe",
            )
            ReviewRow(
                label = "Email",
                value = "john.doe@example.com",
            )
        }
    }
}
