package com.example.composetraining.session1.session1_2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.R
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐ BÀI TẬP 2: Contact Card (Medium)
 *
 * Yêu cầu:
 * - Row chứa: Image (CircleShape, 60dp) + Column (Name bold + Bio regular)
 * - Button "Follow" ở dưới, fillMaxWidth
 * - Card có elevation, rounded corner 12dp
 * - Modifier chain ít nhất 3 modifier
 * - Dùng cả Column, Row, Box
 * - @Preview với showBackground = true
 */

@Composable
fun ContactCard(
    name: String,
    bio: String,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                // TODO: [Session 1] Bài tập 2 - Thêm avatar (Icon hoặc Image)
                // Gợi ý: Icon với modifier .size(60.dp).clip(CircleShape).background(...)
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "avatar",
                    modifier =
                        Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                )

                // TODO: [Session 1] Bài tập 2 - Thêm Column chứa Name (Bold) và Bio (Regular)
                // Gợi ý: Column(modifier = Modifier.weight(1f).padding(start = 12.dp))
                Column(
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(start = 12.dp),
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    )

                    Text(
                        text = bio,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // TODO: [Session 1] Bài tập 2 - Thêm Button "Follow" với fillMaxWidth
            Button(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                Text(text = stringResource(R.string.follow))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactCardPreview() {
    ComposeTrainingTheme {
        ContactCard(
            name = stringResource(R.string.user_name),
            bio = stringResource(R.string.user_name),
        )
    }
}
