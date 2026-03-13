package com.example.kmptraining.migrate.session3.session3_4.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session3.session3_4.FormState
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

// ─── Success Screen ───────────────────────────────────────────────────────────
@Composable
fun SubmissionSuccessScreen(
    formState: FormState,
    modifier: Modifier = Modifier,
) {
    // - Column(fillMaxSize, padding=32.dp, Center, CenterHorizontally)
    // - Surface icon (80dp, extraLarge, primaryContainer) { Box(Center) { Icon(Check, 48dp) } }
    // - Spacer(24.dp) + Text "Registration Complete!" (headlineMedium)
    // - Spacer(8.dp) + Text "Welcome, ${firstName} ${lastName}!" (bodyLarge, onSurfaceVariant)
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Registration Complete!",
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Welcome, ${formState.firstName} ${formState.lastName}!",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true, name = "Success Screen Preview")
@Composable
private fun SuccessScreenPreview() {
    ComposeTrainingTheme {
        SubmissionSuccessScreen(
            formState = FormState(firstName = "John", lastName = "Doe"),
        )
    }
}
