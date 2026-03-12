package com.example.kmptraining.migrate.session1.session1_2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme
import kmptraining.composeapp.generated.resources.Res
import androidx.compose.ui.tooling.preview.Preview
import kmptraining.composeapp.generated.resources.follow
import kmptraining.composeapp.generated.resources.user_bio
import kmptraining.composeapp.generated.resources.user_name
import org.jetbrains.compose.resources.stringResource

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
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "avatar",
                    modifier =
                        Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                )

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

            Button(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                Text(text = stringResource(Res.string.follow))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactCardPreview() {
    ComposeTrainingTheme {
        ContactCard(
            name = stringResource(Res.string.user_name),
            bio = stringResource(Res.string.user_bio),
        )
    }
}