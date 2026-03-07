package com.example.composetraining.session3.session3_4.component.reviewStep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.session3.session3_4.FormState
import com.example.composetraining.ui.theme.ComposeTrainingTheme

// ─── Step 4: Review ───────────────────────────────────────────────────────────

@Composable
fun ReviewStep(
    state: FormState,
    modifier: Modifier = Modifier,
) {
    // - Column(fillMaxSize, verticalScroll, spacedBy=16.dp)
    // - Text "Review your information" (titleMedium)
    // - ReviewSection("Personal Info") { ReviewRow("Name", ...) + ReviewRow("Birth Year", ...) }
    // - ReviewSection("Contact") { email, phone, city }
    // - ReviewSection("Preferences") { newsletter, notifications, language }
    // - Text "Nhấn Submit để hoàn tất" (bodySmall, onSurfaceVariant)
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            "Review your information",
            style = MaterialTheme.typography.titleMedium
        )
        ReviewSection("Personal Info") {
            ReviewRow("Name", state.firstName + " " + state.lastName)
            ReviewRow("Birth Year", state.birthYear)
        }
        ReviewSection("Contact") {
            ReviewRow("Email", state.email)
            ReviewRow("Phone", state.phone)
            ReviewRow("City", state.city)
        }
        ReviewSection("Preferences") {
            ReviewRow("Newsletter", state.email)
//            ReviewRow("Notifications", state.receiveNotifications)
            ReviewRow("Language", state.preferredLanguage)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewStepPreview() {
    ComposeTrainingTheme {
        ReviewStep(
            state = FormState(
                firstName = "John",
                lastName = "Doe",
                birthYear = "1990",
                email = "john.doe@example.com",
                phone = "0123456789",
                city = "Hanoi",
                receiveNewsletter = true,
                receiveNotifications = true,
                preferredLanguage = "English"
            )
        )
    }
}